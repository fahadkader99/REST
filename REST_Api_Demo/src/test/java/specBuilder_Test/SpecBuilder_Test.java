package specBuilder_Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import pojo_serialized.Add_Place;
import pojo_serialized.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilder_Test {

    @Test
    public void request_response_specbuilder_test(){     // Sending REQUEST

        Add_Place addPlace = new Add_Place();   // creating object for the class to work with

        addPlace.setAccuracy(50);
        addPlace.setAddress("40 Metropolitan");
        addPlace.setLanguage("Bangla");
        addPlace.setPhone_number("302-xxx-xxxx");
        addPlace.setWebsite("www.google.com");
        addPlace.setName("Metro House");

        // Types is expecting a list, so we are creating & adding data into list, later we will pass the list here
        List<String> list = new ArrayList<>();
        list.add("Adidas");
        list.add("Nike");
        addPlace.setTypes(list);

        // Location is expecting Location class, so we need to create object of the class
        Location location = new Location();
        location.setLat(2.234);
        location.setLng(3.345);
        addPlace.setLocation(location);


        /*
        Once all the object are set by Serialized_PoJo class, just pass the Object into the Body.
         */



        /*   -------------------     -------------------   -------------------   -------------------                 */


        /* Building generic spec methods */

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();      // GIVEN

        ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();    // THEN

        /*   -------------------     -------------------   -------------------   -------------------                 */


        // 1st- I am building the Request, Response specification & 2nd i am passing the re-usable req, res spec methods

        RequestSpecification request = given().spec(req).body(addPlace);
        Response response = request.when().post("/maps/api/place/add/json").then().spec(res).extract().response();

        // print the results
        String responseString = response.asString();
        System.out.println("--> Using Spec Builder --> " + responseString);




    }

}
