package utils;

import io.restassured.path.json.JsonPath;

import java.util.Random;

public class Reusable {


    public static JsonPath rawToJason(String response){

        JsonPath js = new JsonPath(response);
        return js;
    }

    public static String randomNum(int length){
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk";

        Random rand = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i=0; i<length; i++){
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }

        return sb.toString();

    }
}
