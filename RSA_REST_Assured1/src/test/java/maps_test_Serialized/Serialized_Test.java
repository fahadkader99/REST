package maps_test_Serialized;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import pojo_serialization.Add_Place;
import pojo_serialization.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class Serialized_Test {

    /*Serialization we set the values so use: Setter && De-serialization we get the values so use Getters*/



    @Test        // Serialization :::
    public void add_place_serialization_test(){

        RestAssured.baseURI = "https://rahulshettyacademy.com";


        // creating object for Serialization of the class || need Setter for Serialization
        Add_Place ap = new Add_Place();
        ap.setAccuracy(50);
        ap.setName("Oval House");
        ap.setPhone_number("3473473478");
        ap.setAddress("40 Metropolitan Oval");
        ap.setWebsite("https://rahulshettyacademy.com/");
        ap.setLanguage("English");

        // Json with array / List
        List<String> myList = new ArrayList<String >();
        myList.add("shoe park");
        myList.add("shop");

        ap.setTypes(myList);                                                    // to add value to the List we have to create a list add value and pass that list


        // Json with sub json + subclass - need to create subclass object first
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        ap.setLocation(location);                                               // after setting values to our subclass object we just pass the class to the setLocation.


        /* Now I have created java object by passing all the values of Json with supported POJO class. (above) */


        Response res = given().queryParam("key","qaclick123").body(ap)                      // passing the object we have created for main POJO class -> to body
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract().response();

        String responseString = res.asString();
        System.out.println(responseString);

    }
}
