package automation.step_def;

import automation.utils.Utils;
import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.API_Resources;
import test_data.TestDataBuild;

import static io.restassured.RestAssured.given;

public class Step_Def extends Utils {                         // Stef-Def only holds the core logics & we are inheriting all the property of Utils

    // Global variable:
    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    static String place_id;
    TestDataBuild data = new TestDataBuild();


    @Given("Add Place Payload with {string} {string} {string} {string} {string}")
    public void add_place_payload_with(String name, String phone_number, String address, String language, String website) {

        res = given().spec(requestSpecification())
                .body(data.addPlace_Payload(name, phone_number, address, language, website));        // now it became a request specification & res is re-usable
    }


    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {

        // Enum constructor will be called when .valueOf is used.
        API_Resources resourceAPI = API_Resources.valueOf(resource);            // calling and passing resource dynamically through ENUM
        String resources = resourceAPI.getResource();                           // It will automatically call -> add / get / delete API
        System.out.println("HTTP Method Performing: " + resources);

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if (httpMethod.equalsIgnoreCase("POST")) {
            response = res.when().post(resources);                  // by using res, we are making response.
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response = res.when().get(resources);
        } else if (httpMethod.equalsIgnoreCase("DELETE")) {
            response = res.when().delete(resources);
        }


    }


    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {

        int statusCode = response.getStatusCode();              // using the reusable method to parse the json
        Assert.assertEquals(200,statusCode);
    }


    @Then("verify {string} in response body is {string}")
    public void verify_in_response_body_is(String keyValue, String expectedValue) {

        String retrievedValue = getJsonPath(response, keyValue);           // get will give the value of the given key & then i have to compare the value with the value i have with assertion

        Assert.assertEquals(retrievedValue, expectedValue);             // Asserting with the value i retrieved with the expected value;

    }


    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resourceName) {
        /* Task: create place using AddPlaceAPI and pick the Place_ID and use it to GetPlaceAPI and verify (End to End)*/
        // this is End-to-End functionality test here.

        // 1. prepare request spec
        place_id = getJsonPath(response, "place_id");         // using the reusable method to parse the json

        res = given().spec(requestSpecification()).queryParam("place_id", place_id);

        // 2. Hit the API
        user_calls_with_http_request(resourceName, "GET");      // calling the previous method which is used to hit the api

        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(actualName, expectedName);

    }


    @Given("DeletePlace Payload")
    public void delete_place_payload() {

        res = given().spec(requestSpecification()).body(data.deletePlace_Payload(place_id));

    }


}
