package utils;

import io.restassured.path.json.JsonPath;

public class Reusable_methods {

    public static JsonPath raw_to_JSON(String response){
        JsonPath jasonPath = new JsonPath(response);

        return jasonPath;
    }


}
