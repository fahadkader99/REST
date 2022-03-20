package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestAssuredExtension {
    /*this class will be holding all of our different methods that we will be working with
    * This class will have all the arrange and assert for us*/

    public static RequestSpecification Request;

    public RestAssuredExtension(){

        // Arrange options
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:3000");
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();                                                             // requestSpecification class is important it is a extension from RequestSender class: it has body | query parameter | cookies | config | header param | content types and other
        Request = RestAssured.given().spec(requestSpec);

        // this constructor will be used so that we can create rest of our methods |


    }

    public static void GetopsWithPathParameter( )



}
