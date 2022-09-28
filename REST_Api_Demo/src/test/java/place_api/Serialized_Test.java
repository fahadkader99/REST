package place_api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo_serialized.Add_Place;
import pojo_serialized.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;         // given when then belong to static package and have to import manually


public class Serialized_Test {

    /*Serialization we set the values so use: Setter && De-serialization we get the values so use Getters*/



    @Test
    public void add_place_serialization_test(){     // Sending REQUEST

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

        RestAssured.baseURI = "https://rahulshettyacademy.com";


       Response res =  given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json").body(addPlace)
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract().response();

       String response = res.asString();
       System.out.println("--> Response " + response);
    }
}
