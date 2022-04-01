package place_api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.testng.annotations.Test;
import utils.Payload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class Maps_Basic {

    @Test
    public void place_test() {
        // validate if add place api is working as expected

        /*Given - all the input details to submit the API
        * When - submit the API (resource + http method)
        * THen - Validate the response*/

        RestAssured.baseURI = "https://rahulshettyacademy.com";



        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(Payload.addPlace())
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().body("scope", equalTo("APP")).body("status",equalTo("OK")).header("Server","Apache/2.4.18 (Ubuntu)").extract().response().asString();

        System.out.println("\n-->Add Place API: \n"+response);

        /* *************************************************************************************************************************************************/

        //--- Add place(POST) -> Update (PUT) the place with new address -> validate the place is updated using (GET) Place --- End to End Test


        // Extract the value(place_id) from the JSON:
        JsonPath js = new JsonPath(response);                   // Json Path class for parsing Json
        String placeID = js.get("place_id").toString();

        System.out.println("\n-->Place_ID: "+ placeID);


        //--- Now Update the address using the PlaceID (PUT) --

        String newAddress = "44 Metropolitan Oval";
        String updatePlace = "{\n" +
                "\"place_id\":\""+placeID+"\",\n" +
                "\"address\":\""+newAddress+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";

        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(updatePlace)
                .when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));





        // -- Now validate the address is updated - using (Get) --


        String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeID)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        System.out.println("\n-->Get Place API\n"+getPlaceResponse);

        JsonPath js1 = new JsonPath(getPlaceResponse);
        String getAddress = js1.get("address").toString();

        Assert.assertEquals(newAddress,getAddress);

    }


}
