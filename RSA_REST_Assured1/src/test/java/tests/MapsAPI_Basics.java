package tests;

import utils.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import utils.Reusable_methods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;


public class MapsAPI_Basics {


    /* TASK::: Add place -> Update place with New address -> Get place & validate if New Address which was updated on the update place is present in response of Get Place ---- > End to End task */


    @Test
    public void Place_Modification() {
        /*Given - all input details
         * When - submit the API + resources + http methods
         * THen  - validate the Response */


        /* 1.  Add place to the End Point*/

        RestAssured.baseURI = "https://rahulshettyacademy.com";                                                         // this is our base URI


        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(Payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200);




        /* validation of its body and header*/

        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(Payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)");


        // =============================================================================================================


        /* 2. Take out the place ID to modify place || update || delete place with the ID*/

        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(Payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();                     // extracting the string and saving it

        System.out.println(response);


        // Parse String to JSON.
        JsonPath js = Reusable_methods.raw_to_JSON(response);                                                                       // this class is for Parsing Json -> pass Json as the parameter. It will convert the sting in to Json and save it to the object JS...>!
        String placeID = js.getString("place_id");                                                                  // Extract the place id from the Json

        //System.out.println(placeID);                                                                                    // we took the place ID as we can search and update the place based on their ID.


        // =============================================================================================================


        /* 3 - Update the Place using Place ID*/

        String newAddress = "Metropolicatan Oval";

        String updateBody = "{\n" +
                "\"place_id\":\"" + placeID + "\",\n" +                                                                      // checking with the previous / new place ID
                "\"address\":\"" + newAddress + "\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(updateBody)
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));


        // =============================================================================================================



        /* 4 - Get Place and validate the updated address */

        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)                                // in Get -> we do not pass header | or Body but pass all the query param
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().asString();


        JsonPath jsonPath = Reusable_methods.raw_to_JSON(getPlaceResponse);
        String actualAddress = jsonPath.getString("address");                                                      // we are taking out the updated Address value from the JSON

        //System.out.println(actualAddress);

        Assert.assertEquals("Address is miss-matching", newAddress, actualAddress);                             // validating that address has updated.


        System.out.println("<<<< All is Well >>>>");


    }

    // we are passing the static JSON file from external file || look at the body where we are passing the file
    @Test
    public void addBook_Static_json_payload() throws IOException {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(new String(Files.readAllBytes(Paths.get("/Users/fahadkader/Desktop/Udemy/Rest_Assured_Automation/Test_data/test1.json"))))
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("Server", "Apache2.4.18 (Ubuntu)").extract().response().asString();


        System.out.println(response);
        JsonPath jsonPath =  new JsonPath(response);
        String placeId = jsonPath.getString("place_id");
        System.out.println(placeId);


    }


}



