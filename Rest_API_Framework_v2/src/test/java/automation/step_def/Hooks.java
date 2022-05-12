package automation.step_def;


import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario(){
        // We have to write a code that will give us place id & execute only when place id in null.
        // So my logic have to be intelligent, so that it will only run when no place id is there, but when running all test & if place id is there, it will not run
        // Now I am going to call all the step def methods, that is used to create the place_id


        Step_Def stepDef = new Step_Def();

        if (Step_Def.place_id==null){

            stepDef.add_place_payload_with("Fahad","2222222222", "Metropolitan", "English", "http://ebay.com");

            stepDef.user_calls_with_http_request("AddPlaceAPI","POST");

            stepDef.verify_place_id_created_maps_to_using("Fahad","GetPlaceAPI");
        }

    }

    @After
    public void afterScenario(){
        // Use the concept to implement anything that I might need for further scenarios. For-now it is empty
    }
}
