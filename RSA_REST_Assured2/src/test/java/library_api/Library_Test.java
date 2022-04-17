package library_api;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import utils.Payload;
import utils.Reusable_Methods;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Library_Test {

    @Test
    public void addBook(){
        RestAssured.baseURI = "http://216.10.245.166";

        String bookResponse = given().log().all().header("Content-Type","application/json").body(Payload.addBookLibrary("xwp","195"))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();


        JsonPath js = Reusable_Methods.rawToJson(bookResponse);
        String id = js.get("ID").toString();
        System.out.println(id);

    }

    @Test
    public void bookTest_sendStaticPayload(){

        RestAssured.baseURI = "http://216.10.245.166";
        String response = "";

        try {
            response = given().header("Content-Type","application/json").body(Files.readAllBytes(Paths.get("src/test/resources/test_data/addBookLibrary.json")))
                    .when().post("/Library/Addbook.php")
                    .then().log().all().assertThat().statusCode(200).extract().response().asString();
        }catch (Exception e){
            e.printStackTrace();
        }


        JsonPath js = Reusable_Methods.rawToJson(response);
        String id = js.get("ID").toString();
        System.out.println(id);

    }
}
