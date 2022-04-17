package place_api;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import utils.Payload;

public class Complex_JsonParse {


    @Test
    public void course_Test() {

        JsonPath js = new JsonPath(Payload.coursePrice());              // this is the MOCK data | response.
        //Now we are working with MOCK data as Dev are building their API


        /* 1- print no of course returned by API*/

        int courseCount = js.getInt("courses.size()");
        System.out.println(courseCount);


        /* 2- Print purchase amount*/

        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);


        /* 3- Print title of first course*/

        String title = js.getString("courses[0].title");
        String title2 = js.getString("courses[1].title");
        System.out.println(title);
        System.out.println(title2);


        /* 4- Print all course titles and their respective prices*/

        System.out.println("\nAll course Titles and Price:");
        for (int i = 0; i< courseCount; i++){
            System.out.println(js.getString("courses["+i+"].title"));
            System.out.println(js.get("courses["+i+"].price").toString());
        }


        /* 5- print no of copies sold by RPA course*/

        String targetTitle = "RPA";

        System.out.println("\nFind Target Title + It's Price");
        for (int i = 0; i< courseCount; i++ ){
            if (js.getString("courses["+i+"].title").equalsIgnoreCase(targetTitle)){
                System.out.println(js.get("courses["+i+"].copies").toString());
                break;
            }

        }


    }

    @Test
    public void validate_Sum_Prices(){
        /* 5- verify if sum of all Course prices match with purchases Amount*/

        JsonPath js = new JsonPath(Payload.coursePrice());
        int courseCount = js.getInt("courses.size()");
        int totalAmount = js.getInt("dashboard.purchaseAmount");

        int totalSum = 0;
        for (int i = 0; i<courseCount; i++){
            totalSum += js.getInt("courses["+i+"].price") * js.getInt("courses["+i+"].copies");
        }



        System.out.println("\nValidating the prices with the Purchase Amount");             // Purchase amount = Copies * price

        if (totalSum==totalAmount){
            System.out.println("Amounts are equal");
        }else {
            System.out.println("Amount are not equal...!");
        }
    }
}
