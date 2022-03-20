package steps;
// https://www.youtube.com/watch?v=Inwycw_Cpzk&list=PL6tu16kXT9PpgqfMbMdzUzDenYgb0gbk0&index=4

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class GetPostSteps {

    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) {

    }

    @And("I perform GET for the post number {string}")
    public void iPerformGETForThePostNumber(String postNumber) {
        BDD_Style_Methods.simpleGetPost(postNumber);
    }

    @Then("I should see the author name as {string}")
    public void iShouldSeeTheAuthorNameAs(String arg0) {
        System.out.println("Can't implement API Gherkin, because of its limitation with the Cucumber Gherkin");

    }

    @Then("I should see the author names")
    public void iShouldSeeTheAuthorNames() {
        BDD_Style_Methods.performContainsCollection();
    }

    @Then("i should see Verify GET Parameter")
    public void iShouldSeeVerifyGETParameter() {
        //BDD_Style_Methods.performPathParameter();
        BDD_Style_Methods.performQueryParameter();
    }
}
