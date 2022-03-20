package tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Payload;
import utils.Reusable_methods;

import static io.restassured.RestAssured.*;

public class LibraryAPI_Dynamic_JSON {

    /* Given - all input details
     * When - submit the API (http methods + resources)
     * THen  - validate the Response */

    @Test
    public void addBook_Dynamically_Json_Payload() {

        // 1 - Add book  +  get the ID  +  Dynamically send data :

        RestAssured.baseURI = "http://216.10.245.166";

        String bookResponse = given().log().all().header("Content-Type", "application/json").body(Payload.addBook("abx", "3457Fq"))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = Reusable_methods.raw_to_JSON(bookResponse);
        String bookID = js.getString("ID");
        System.out.println("ID: " + bookID);

        // Here - Dynamically build jason payload with external data input - we are passing isbn & aisle number dynamically to the test using reusable method and by sending arguments
        // However, to run out test multiple time we need to AddBook and Delete Book at the same time so that test never fail due to book already exists error


        /*=============================================================================================================*/

    }


    // 2 - Parameterization with multiple data || Part of TestNy Data provider ....!

    @DataProvider(name = "BooksData")
    public Object[][] getData() {

        return new Object[][]{{"qtp", "32T9"}, {"jks", "10m9"}, {"pcq", "5y9t"}};                                           // Array = collection of elements &&  Multidimensional array = collection of arrays
        // we are creating arrays of data values, which will pe passed || test will run 3 times

    }

    @Test(dataProvider = "BooksData")
    public void addBook_Parameterization_test_Multiple_Data(String isbn, String aisle) {

        // Add Book -- with the multiple test data
        RestAssured.baseURI = "http://216.10.245.166";

        String bookResponse = given().log().all().header("Content-Type", "application/json").body(Payload.addBook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = Reusable_methods.raw_to_JSON(bookResponse);
        String bookID = js.getString("ID");
        System.out.println("ID: " + bookID);


    }

    @Test(dataProvider = "BooksData")
    public void deleteBook_Parameterization_test_Multiple_Data(String isbn, String aisle) {

        // Delete -- all the book that were created above : For Genuine test case we have to add and delete so that test can be run multiple time
        RestAssured.baseURI = "http://216.10.245.166";

        given().header("Content-Type", "application/json").body(Payload.addBook(isbn, aisle))
                .when().post("/Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200);


    }
    /*=============================================================================================================*/




}
