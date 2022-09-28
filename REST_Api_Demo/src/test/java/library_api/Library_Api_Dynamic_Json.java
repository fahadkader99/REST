package library_api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Payload;
import utils.Reusable;

import static io.restassured.RestAssured.*;

public class Library_Api_Dynamic_Json {
    /* Given - all input details
     * When - submit the API (http methods + resources)
     * THen  - validate the Response */

    @Test
    public void addBook_Dynamically_Json_Payload(){

        // 1. Add book + get the ID + Dynamically send data

        RestAssured.baseURI = "http://216.10.245.166";
        String  bookResponse =  given().log().all().header("Content-Type", "application/json").body(Payload.addBook(Reusable.randomNum(3), Reusable.randomNum(3)))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = Reusable.rawToJason(bookResponse);
        String bookID = js.get("ID");
        System.out.println("--> Book ID " + bookID);

        // Here - Dynamically build jason payload with external data input - we are passing isbn & aisle number dynamically to the test using reusable method and by sending arguments
        // However, to run out test multiple time we need to AddBook and Delete Book at the same time so that test never fail due to book already exists error
    }

    /*
    ====================================================================================================================================================================================
     */




    // 2 - Parameterize test with multiple data || Part of TestNy Data provider ....! ----> (Run single test with multiple combination of data & create multiple ID's)


    @DataProvider
    public Object[][] getData(){          // need to send the data in multi-dimentional array

         return new Object[][] {{"abcd", "392"}, {"hksjf", "345"}, {"kajdf", "984"}, {"jks", "10m9"}, {"pcq", "5y9t"}};
        // we are creating 2D arrays of data values, which will be passed. Test will run 3 times
    }


    @Test(dataProvider = "getData")     // just put the dataProvider method name to link.
    public void addBook_Parameterized_test_Multiple_Data(String isbn, String aisle){
        // add book - with multiple test data

        RestAssured.baseURI = "http://216.10.245.166";
        String  bookResponse =  given().log().all().header("Content-Type", "application/json").body(Payload.addBook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = Reusable.rawToJason(bookResponse);
        String bookID = js.get("ID");
        System.out.println("--> Book ID " + bookID);
    }


    @Test(dataProvider = "getData")
    public void deleteBook_Parameterized_test_Multiple_Data(String isbn, String aisle){

        RestAssured.baseURI = "http://216.10.245.166";

        // Delete -- all the book that were created above : For Genuine test case we have to add and delete so that test can be run multiple time

        given().header("Content-Type", "application/json").body(Payload.addBook(isbn, aisle))
                .when().post("Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200);
    }

}
