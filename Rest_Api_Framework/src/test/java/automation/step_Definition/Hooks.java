package automation.step_Definition;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.StepDefinitionAnnotation;

public class Hooks {

    @Before("@DeletePlace")
    public void before_Scenario() {

    /* - write code that gives you place_id ---- access the methods of the step_def
       - execute the code only when place_id is null */

        AddPlace_Steps addPlaceSteps = new AddPlace_Steps();
        // creating the object for AddPlace Step_Definition so that I can use there methods

        if (AddPlace_Steps.place_id == null) {

            // re-using all the methods from step_def to generate Place_Id so that Delete Place API can run independently
            addPlaceSteps.add_place_payload_with("Esrat Jahan", "30 Brooklyn ", "Arabic", "http://youtube.com");       // we can pass any data just to get the place_id that is generated.
            addPlaceSteps.user_calls_with_http_request("AddPlaceAPI", "POST");
            addPlaceSteps.verify_place_id_created_maps_to_using("Esrat Jahan", "GetPlaceAPI");
        }


    }


    @After
    public void after_Scenario() {
        // Use the concept to implement anything that I might need for further scenarios. For-now it is empty
    }

}
