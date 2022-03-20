package steps;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.StringContains.containsString;

public class BDD_Style_Methods {

    public static void simpleGetPost(String postNumber) {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(String.format("http://localhost:3000/posts/%s", postNumber))
                .then().body("author", is("Kader")).statusCode(200);
        // String.format will pass the postNumber to the url

    }

    public static void performContainsCollection() {                                                // verifying all the collection has this mentioned names
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/posts/")
                .then()
                .body("author", containsInAnyOrder("Kader", "Fahad", "Esrat", "Jahan", "Ashikul")).statusCode(200);
    }


    public static void performPathParameter() {
        given()
                .contentType(ContentType.JSON)
                .with()
                .pathParams("id", 2)
                .when()
                .get("http://localhost:3000/posts/{id}")
                .then()
                .body("author", containsString("Kader"));


    }
    public static void performQueryParameter(){
         given()
                 .contentType(ContentType.JSON)
                 .queryParam("id","1")
                 .when()
                 .get("http://localhost:3000/posts/")
                 .then()
                 .body("author",hasItem("Ashikul"));
    }


}
