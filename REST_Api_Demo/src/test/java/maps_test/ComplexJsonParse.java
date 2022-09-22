package maps_test;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Payload;

public class ComplexJsonParse {


    @Test
    public void parse_Json(){

        JsonPath js = new JsonPath(Payload.CoursePrice());

        // 1. print no of course returned by API
        int count = js.getInt("courses.size()");            // array.size()
        System.out.println("\nCount of courses " + count);


        // 2. Print purchase amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount " + purchaseAmount);


        // 3. Print title of first course
        String courseTitle = js.getString("courses[0].title");
        System.out.println("Course Title " + courseTitle+"\n");


        // 4. Print all course titles and their respective prices
        for (int i=0; i<count; i++){
            String titles = js.get("courses["+i+"].title");
            int prices = js.get("courses["+i+"].price");

            System.out.println("Titles " + titles);
            System.out.println("Prices " + prices);
        }


        // 5. print no of copies sold by RPA course (Make dynamic search)
        int rpaCourse = js.getInt("courses[2].copies");
        System.out.println("\nRPA course copies " + rpaCourse);

        /*
            Dynamically solving : number of copies sold by RPA course
         */

        for (int i=0; i<count; i++){
            String title = js.getString("courses["+i+"].title");
            if (title.equalsIgnoreCase("RPA")){
                int copy = js.getInt("courses["+i+"].copies");
                System.out.println("RPA copy -> " + copy);
                break;
            }
        }


       // 6. verify if sum of all Course prices match with purchases Amount


        int totalprice = 0;
        int totalAmount = 0; 



        for (int i=0; i<count; i++){
            int prices = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            totalprice= prices*copies;
         
            totalAmount += totalprice;

        }

        System.out.println("\nActual Total Amount = " + totalAmount);
        Assert.assertEquals(purchaseAmount, totalAmount, "Total amount isn't patching for Purchase copies");




    }


}
