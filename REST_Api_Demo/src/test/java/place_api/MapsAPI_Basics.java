package place_api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Payload;
import utils.Reusable;

import static io.restassured.RestAssured.*;         // given when then belong to stati package and have to import manually
import static org.hamcrest.Matchers.*;              // manually import this static package for Json body matcher


public class MapsAPI_Basics {

    @Test
    public void Place_Modification() {

        // Syntax principle

        /*
        Given - Submit all input details    (parameter + header + body)
        When - Submit the API   (resource + http method) -> other than this 2 all other remaining input details should go to GIVEN
        Then - validate the response
         */


        // validate if add place API is working

        RestAssured.baseURI = "https://rahulshettyacademy.com";


//
//        /*
//        1. Add place to the End Point - redundent to the following method
//        */

//        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(Payload.addPlace())
//                .when().post("maps/api/place/add/json")
//                .then().log().all().assertThat().statusCode(200);
//
//
//
//        /* validation of its body and header*/
//
//        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
//                .body(Payload.addPlace())
//                .when().post("/maps/api/place/add/json")
//                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("server","Apache/2.4.41 (Ubuntu)");   // validate json body parameter.


        // =============================================================================================================


        /*
         2. Take out the place ID -> extract with JsonPath
         */

        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Payload.addPlace())
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString(); // extracting the response in string

        System.out.println("Response: -> " + response);     // need to parse the json to extract the place_id

        // this class is use for parsing Json
        JsonPath js = Reusable.rawToJason(response);
        String placeID = js.getString("place_id");                       // path for json always start from parent for extracting
        System.out.println("++ Place ID -> " + placeID);



        // =============================================================================================================


        /*
         3 - Update the Place using Place ID
         */

        String newAddress = "60 metropolitan";
        String updatedBody = "{\n" +
                "\"place_id\":\""+placeID+"\",\n" +
                "\"address\":\""+newAddress+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}\n";

        // updating address & validating in the body.
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(updatedBody)
                .when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));


        // =============================================================================================================


        /*
        4 - Get Place and validate the address is updated.!
        */

        // Now Get API + extract the new address.

        String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeID)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().asString();

        System.out.println("++ Place Response -> " + getPlaceResponse);

        // Extract & validate place
        JsonPath js1 = Reusable.rawToJason(getPlaceResponse);
        String actualAddress = js1.getString("address");
        Assert.assertEquals(actualAddress,newAddress, "Address isn't matching ");

        System.out.println("---- All Set -----");

    }


    /*
    How to send RAW Json from external file system to script

    1. content of the file to String > content of the file can convert into byte >Byte data to String
    2. Once the filePath become string then pass it to the body(....)

        .body(new String(Files.readAllBytes(Paths.get("C:\\user\\Documents\\........payload.json"))))           // this is how to send RAW json file
     */





}
