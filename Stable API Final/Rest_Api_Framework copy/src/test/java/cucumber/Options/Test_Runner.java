package cucumber.Options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"src/test/resources/features/Place_Validation.feature"},
        glue = {"automation/step_Definition"},
        plugin = {"pretty","json:target/jsonReports/cucumber-report.json"}
        //tags = "@DeletePlace"


)

public class Test_Runner {
}
