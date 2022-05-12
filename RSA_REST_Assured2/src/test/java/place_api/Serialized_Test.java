package place_api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
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


            // other way of doing the same acton are given below: by breaking it down further.
/*        Response res = given().log().all().queryParam("key","qaclick123").body(addPlace)
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract().response();*/



        // more Optimised using --> Request & Response Spec builder,

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();



        RequestSpecification res = given().spec(req).body(addPlace);        // now it became a request specification & res is re-usable

        Response response = res.when().post("/maps/api/place/add/json")     // by using res, we are making response.
                .then().spec(resSpec).extract().response();



        String responseString = response.asString();
        System.out.println(responseString);



    }
}
