package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"src/test/resources/features/Place_Validation.feature"},
        glue = {"automation/step_def"},
        plugin = {"pretty"}
)


public class TestRunner {
}
