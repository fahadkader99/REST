package cucumber.Options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"src/test/resources/features/Place_Validation.feature"},
        glue = {"automation/step_Definitions"},
        plugin = {"pretty"}


)

public class Test_Runner {
}
