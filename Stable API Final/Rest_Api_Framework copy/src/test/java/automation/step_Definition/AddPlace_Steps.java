package automation.step_Definition;

import automation.utils.Common_Methods;
import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.API_Resources;
import test_Data.Test_Data_Build;

import static io.restassured.RestAssured.given;


public class AddPlace_Steps extends Common_Methods {

    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    static String place_id;


    Test_Data_Build testDataBuild = new Test_Data_Build();


    @Given("Add Place Payload with {string} {string} {string} {string}")
    public void add_place_payload_with(String name, String address, String language, String website) {

        res = given().spec(request_Specification()).body(testDataBuild.addPlace_Payload(name, address, language, website));

    }


    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {

        API_Resources apiResources = API_Resources.valueOf(resource);     // enum + Constructor will be called with ValueOf method with the resource which you passed
        System.out.println(apiResources.getResource());                   //  checking the Enum resource

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();


        if (method.equalsIgnoreCase("POST")) {
            response = res.when().post(apiResources.getResource());              // passing the Enum method here
        }
        else if (method.equalsIgnoreCase("GET")) {
            response = res.when().get(apiResources.getResource());
        }
        else if (method.equalsIgnoreCase("DELETE")){
            response = res.when().get(apiResources.getResource());
        }



    }


    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {

        Assert.assertEquals(response.getStatusCode(), 200);

    }


    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {

        // getting the Json from the utility
        String actualKey = getJson_Path(response, keyValue);

        Assert.assertEquals(actualKey, expectedValue);
    }


    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String methodName) {
        /* Task: create place using AddPlaceAPI and pick the Place_ID and use it to GetPlaceAPI and verify (End to End)*/

        // 1. RequestSpec creation and get the place_id
        place_id  = getJson_Path(response, "place_id");               // extracting place_id from utility method

        res = given().spec(request_Specification()).queryParam("place_id", place_id);


        // 2. Hit the api - for that we have to call the previous method
        user_calls_with_http_request(methodName, "GET");                   // now it is holding the Response payload, now we have to check the name :
        String actualName = getJson_Path(response, "name");                    // extracting the name from the response payload now

        Assert.assertEquals(actualName, expectedName);


    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() {

        res = given().spec(request_Specification()).body(testDataBuild.deletePlace_Payload(place_id));
    }

}
