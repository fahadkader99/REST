package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class CheckingAccountsSteps {

    private String authenticationUri = "http://3.129.60.236:8080/bank/api/v1/auth";

    private Response authResponse;
    private Response response;
    @Given("^the admin user is authenticated$")
    public void the_admin_user_is_authenticated() {
        //
        authResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .baseUri(authenticationUri)
                .queryParam("username", "admin@demo.io")
                .queryParam("password", "Demo123!")
                .when()
                .request("POST");

        System.out.println(authResponse.getBody().asPrettyString());

    }

    @When("^I send a the following create account payload$")
    public void i_send_a_the_following_create_account_payload(List<Map<String, String>> createAccountPayload) {

        JsonPath jsonPath = authResponse.jsonPath();
        System.out.println(jsonPath.getString("authToken"));
        String uri = "http://3.129.60.236:8080/bank/api/v1/user/75/account";

        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .baseUri(uri)
                .header("Authorization", "Bearer " + jsonPath.getString("authToken"))
                .body(createAccountPayload.get(0))
                .when()
                .request("POST");

        System.out.println(response.getBody().asPrettyString());
    }

    @Then("^response status code should be '(\\d+)'$")
    public void response_status_code_should_be(int statusCode) {
            //assert response
    }

    @Then("^the following should be the response payload$")
    public void the_following_should_be_the_response_payload(List<Map<String, String>> createAccountResponsePayload) {
         //assert each field
        //this is how you get your expected result
        Assert.assertEquals(createAccountResponsePayload.get(0).get("name"), response.jsonPath().getString("name"));
        System.out.println(createAccountResponsePayload.get(0).get("name"));
    }

    @Then("^the following should be the accountType response payload$")
    public void the_following_should_be_the_accountType_response_payload(List<Map<String, String>> accountTypeResponsePayload) {

    }

    @Then("^the following should be the ownershipType response payload$")
    public void the_following_should_be_the_ownershipType_response_payload(List<Map<String, String>> accountOwnershipTypeResponsePayload) {

    }

    @Then("^the following should be the accountStanding response payload$")
    public void the_following_should_be_the_accountStanding_response_payload(List<Map<String, String>> accountStandingResponsePayload) {

    }


}
