package automation.utils;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Common_Methods {

    public static RequestSpecification req;


    public RequestSpecification request_Specification() {

        if (req == null) {                                                                                               // if req is null then create log file, else do not create new just return the existing Logging file

            PrintStream log = null;
            try {
                log = new PrintStream(new FileOutputStream("logging.txt"));                                       // Creating file for keeping the log using FileOutputStream
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            req = new RequestSpecBuilder().setBaseUri(getProperties("baseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))                                                // we are keeping Logs: Request + Response on the same file
                    .setContentType(ContentType.JSON).build();

            return req;

        } else {
            return req;
        }

    }


    public static String getProperties(String key) {

        Properties prop = new Properties();

        try {
            FileInputStream file = new FileInputStream("src/test/resources/config/config.properties");
            prop.load(file);
        } catch (Exception e) {
            System.out.println("Exception found at the Property Level: " + e.toString());
            e.printStackTrace();
        }

        return prop.getProperty(key);

    }


    public String getJson_Path(Response response, String key){              // utility method to return Jason key , value

        String resp = response.asString();
        JsonPath js = new JsonPath(resp);

        return js.get(key).toString();

    }


}
