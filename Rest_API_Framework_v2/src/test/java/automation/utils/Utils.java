package automation.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {

    public static RequestSpecification req;

    public RequestSpecification requestSpecification() {
        // this method is using requestSpecification and saving for request and response logs on the same file

        if (req == null) {                  // this condition is helping to write all the logs in one file not to replace them..!
            PrintStream log = null;
            try {
                log = new PrintStream(new FileOutputStream("logging.txt"));         // to write we use fileOutput stream
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            req = new RequestSpecBuilder().setBaseUri(getProperties("baseURI")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();

            return req;
        }
        return req;


    }


    public static String getProperties(String key) {           // we are sending the property KEY as parameter

        Properties prop = new Properties();

        try {
            FileInputStream file = new FileInputStream("src/test/resources/config/global.properties");      // to read we use FileInputStream
            prop.load(file);
        } catch (Exception e) {
            System.out.println("Exception found at the property Level: " + e.toString());
            e.printStackTrace();
        }

        return prop.getProperty(key);

    }


    public String getJsonPath(Response response, String key){

        String resp = response.asString();
        JsonPath js = new JsonPath(resp);

        return js.get(key).toString();

    }


}
