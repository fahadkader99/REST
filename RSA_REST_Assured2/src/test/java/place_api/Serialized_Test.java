package place_api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo_serialized.AddPlace;
import pojo_serialized.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class Serialized_Test {

    public static void main(String[] args) {

        RestAssured.baseURI= "https://rahulshettyacademy.com";

        AddPlace addPlace = new AddPlace();         // we are setting value with the help of Serialization
        addPlace.setAccuracy(55);
        addPlace.setName("Metropolitan Home");
        addPlace.setPhone_number("1234567890");
        addPlace.setAddress("44 Bronx");
        addPlace.setWebsite("https://rahulshettyacademy.com");
        addPlace.setLanguage("English");

        List<String> myList = new ArrayList<>();
        myList.add("Apple");
        myList.add("Ipad Air 5th Gen");

        addPlace.setTypes(myList);

        Location location = new Location();
        location.setLat(-39.39);
        location.setLng(55.59);

        addPlace.setLocation(location);




        Response res = given().log().all().queryParam("key","qaclick124").body(addPlace)
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract().response();

        String responseString = res.asString();
        System.out.println(responseString);



    }
}
