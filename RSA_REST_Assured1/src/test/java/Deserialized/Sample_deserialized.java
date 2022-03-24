package Deserialized;

import io.restassured.parsing.Parser;
import org.testng.annotations.Test;
import pojo_deserialization.GetCourse;

import static io.restassured.RestAssured.*;

public class Sample_deserialized {

    @Test
    public void deserialized_sample_test(){

        GetCourse get = given().queryParam("access_token", "12345").expect().defaultParser(Parser.JSON)
                .when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);



    }
}
