package api.helpers;

import api.constants.EndPoints;
import api.models.Person;
import api.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.internal.org.objectweb.asm.TypeReference;

import java.lang.reflect.Type;
import java.util.List;

public class PersonServiceHelper {
    // Functions - fetch the data from endpoints
    // Get - Post - Put - Patch - Delete
    // All the Request & Response re-useable method stays here


    // Read from config file
    private static final  String BASE_URL = ConfigManager.getInstance().getString("base_url");
    private static final  String PORT = ConfigManager.getInstance().getString("port");

    public PersonServiceHelper(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.port = Integer.parseInt(PORT);
        RestAssured.useRelaxedHTTPSValidation();        // -> Only for localhost perpose
    }

    public List<Person> getAllPerson(){

        Response response = RestAssured.given().contentType(ContentType.JSON).get(EndPoints.GET_ALL_PERSON).andReturn();

        Type type = new TypeReference<List<Person>>(){}.getType();

        List<Person> personList = response.as(type);

        return  personList;
    }

}
