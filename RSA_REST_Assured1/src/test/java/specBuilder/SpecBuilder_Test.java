package specBuilder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Test;
import pojo_serialization.Add_Place;
import pojo_serialization.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilder_Test {

    @Test       // This example is taken from Serialization :::
    public void request_response_spec_Builder() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";


        // creating object for Serialization of the class || need Setter for Serialization
        Add_Place addPlace = new Add_Place();

        addPlace.setAccuracy(50);
        addPlace.setName("Oval House");
        addPlace.setPhone_number("3473473478");
        addPlace.setAddress("40 Metropolitan Oval");
        addPlace.setWebsite("https://rahulshettyacademy.com/");
        addPlace.setLanguage("English");

        // Json with array / List
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        addPlace.setTypes(myList);                                                                                            // to add value to the List we have to create a list add value and pass that list

        // Json with sub json + subclass - need to create subclass object first
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        addPlace.setLocation(location);                                                                                       // after setting values to our subclass object we just pass the class to the setLocation.

        /* Now I have created java object by passing all the values of Json with supported POJO class. (above) */






        // Spec-Builder :::_______________ All the repeated fields goes into Spec Builder class.

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        // above are reusable build.



        RequestSpecification request = given().spec(req).body(addPlace);

        Response response = request.when().post("/maps/api/place/add/json")
                .then().spec(res).extract().response();

        String responseString = response.asString();
        System.out.println("\nUsing Spec Builder: " + responseString);


    }
}
