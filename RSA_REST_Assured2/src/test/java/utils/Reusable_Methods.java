package utils;

import io.restassured.path.json.JsonPath;

public class Reusable_Methods {

    public static JsonPath rawToJson(String resource){

        JsonPath js = new JsonPath(resource);

        return js;
    }
}
