package automation.step_Definitions;

import automation.utils.Common_Methods;
import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import test_Data.Test_Data_Build;

import static io.restassured.RestAssured.given;


public class AddPlace_Steps extends Common_Methods {

    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;

    Test_Data_Build testDataBuild = new Test_Data_Build();


    @Given("Add Place Payload with {string} {string} {string} {string}")
    public void add_place_payload_with( String name, String address, String language, String website) {

        res = given().spec(request_Specification()).body(testDataBuild.addPlace_Payload( name, address, language, website));

    }


    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        response = res.when().post("/maps/api/place/add/json")
                .then().spec(resSpec).extract().response();
    }


    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {

        Assert.assertEquals(response.getStatusCode(),200);

    }


    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {

       String resp =  response.asString();
       JsonPath js = new JsonPath(resp);
       String actualKey = js.get(keyValue).toString();

       Assert.assertEquals(actualKey,expectedValue);
    }
}
