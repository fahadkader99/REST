package utils;

public class Payload {

    public static String addPlace(){
        String addPlaceBody = "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Test Home\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://GOOGLE.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";

        return addPlaceBody;
    }

    public static String coursePrice(){

        String cPrice = "{\n" +
                "  \"dashboard\" : {\n" +
                "    \"purchaseAmount\" : 4225,\n" +
                "    \"website\" : \"https://rahulshettyacademy.com\"\n" +
                "  },\n" +
                "  \"courses\": [\n" +
                "    {\n" +
                "      \"title\": \"Selenium Python\",\n" +
                "      \"price\": 50,\n" +
                "      \"copies\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Cypress\",\n" +
                "      \"price\": 40,\n" +
                "      \"copies\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"RPA\",\n" +
                "      \"price\": 45,\n" +
                "      \"copies\": 10\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Git\",\n" +
                "      \"price\": 30,\n" +
                "      \"copies\": 8\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"MySQL\",\n" +
                "      \"price\": 55,\n" +
                "      \"copies\": 15\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Jenkins\",\n" +
                "      \"price\": 75,\n" +
                "      \"copies\": 30\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        return cPrice;
    }

    public static String addBookLibrary(String isbn, String  aisle){
        String addBook = "{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"Kader foe\"\n" +
                "}";

        return addBook;
    }

}
