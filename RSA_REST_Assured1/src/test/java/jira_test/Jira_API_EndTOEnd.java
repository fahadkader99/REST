package jira_test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class Jira_API_EndTOEnd {

    /*-> Task:
     * 1. Login to jira to create Session ID using Login API
     * 2. Add comment to existing issue using Add comment API
     * 3. Add a attachment to existing issue using Add Attachment API
     * 4. Get issue details and verify if added comment and attachment exists using Get Issue API
     *
     * ==== These are very important for Interview ====== */


    @Test
    public void Jira_API_Test_Automation() {

        RestAssured.baseURI = "http://localhost:8080";

        SessionFilter session = new SessionFilter();

        // 1. ____________________________________________Login & get Session ID____________[http://localhost:8080/rest/auth/1/session]


        String userCredentials = "{ \"username\": \"fahad_ebay\", \"password\": \"Jahan1245\" }";


        given().relaxedHTTPSValidation().header("Content-Type", "application/json").body("{ \"username\": \"fahad_ebay\", \"password\": \"Jahan1245\" }").log().all().filter(session)
                .when().post("/rest/auth/1/session")
                .then().log().all().extract().response().asString();






        // 2.  _________________________Add comment using Session ID_____________________[http://localhost:8080/rest/api/2/issue/10004/comment]

        String expected_Message = "Hi this is Automated : how are you ?";

        String commentBody = "{\n" +
                "    \"body\": \"" + expected_Message + "\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}";

        String addCommentResponse = given().pathParams("key", "10101").log().all().header("Content-Type", "application/json").body(commentBody).filter(session)
                .when().post("/rest/api/2/issue/{key}/comment")
                .then().log().all().assertThat().statusCode(201).extract().response().asString();

        JsonPath js = new JsonPath(addCommentResponse);
        String commentID = js.getString("id");                                                               // extracting id for every comment






        // 3. ______________________________Add Attachments (using curl)________________________upload path__[/Users/fahadkader/Development/Jira_Home/data/attachments]


        /* curl -D- -u admin:admin -X POST -H "X-Atlassian-Token: no-check" -F "file=@myfile.txt" http://myhost/rest/api/2/issue/TEST-123/attachments */

        given().header("X-Atlassian-Token", "no-check").filter(session).pathParams("key", "10101").header("Content-Type", "multipart/form-data")
                .multiPart("file", new File("/Users/fahadkader/Desktop/Git/REST/RSA_REST_Assured1/src/test/java/jira_test/jira.txt"))
                .when().post("/rest/api/2/issue/{key}/attachments")
                .then().log().all().assertThat().statusCode(200);








        // 4. _______[Limiting Json response using Query Param]_________________Get issue Details & validate Comments & Attachments_______________________________


        // get issue
        String issueDetails = given().log().all().filter(session).pathParams("key", "10101").queryParam("fields", "comment")
                .when().get("/rest/api/2/issue/{key}")
                .then().log().all().extract().response().asString();

        System.out.println(issueDetails);






        // validate comments ID :-: of most recent _____ since we have multiple comment : we can track the comment by using there specific ID

        JsonPath js1 = new JsonPath(issueDetails);
        int commentsCount = js1.getInt("fields.comment.comments.size()");                                           // follow json editor online use the path and get the size of the number of comments


        for (int i = 0; i < commentsCount; i++) {                                                                       // for multiple comments but for 1 comment we do not need this step
            //System.out.println(js1.getInt("fields.comment.comments["+i+"].id"));                                      // getting all the comment ID:

            String commentIdIssue = js1.get("fields.comment.comments[" + i + "].id").toString();                        // storing all the comment ID

            if (commentIdIssue.equalsIgnoreCase(commentID)) {                                                           // if both comment ID match then go inside

                String responseMessage = js1.get("fields.comment.comments[" + i + "].body").toString();                 // since the ID matched now we are extracting the body
                System.out.println(responseMessage);

                Assert.assertEquals(responseMessage, expected_Message);                                                  // Now we are validating is the message we passed is matching with the body:::
            }
        }


    }


}
