package automation.step_def;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.Add_Place;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Place_Steps {

    RequestSpecification res;
    ResponseSpecification respec;
    Response response;


    @Given("Add Place Payload")
    public void add_place_payload() {
        // Write code here that turns the phrase above into concrete actions

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

        respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        // above are reusable build.


        res = given().spec(req).body(addPlace);

    }


    @When("User calls {string} with POST http request")
    public void user_calls_with_post_http_request(String string) {
        // Write code here that turns the phrase above into concrete actions

        response = res.when().post("/maps/api/place/add/json")
                .then().spec(respec).extract().response();

    }


    @Then("The API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }


    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        // Write code here that turns the phrase above into concrete actions

        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        String actualValue = js.get(keyValue).toString();

        Assert.assertEquals(actualValue,expectedValue);


    }




}
