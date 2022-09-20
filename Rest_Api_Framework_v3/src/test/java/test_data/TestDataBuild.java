package test_data;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    // Test data are the data that we will be using dynamically throughout our framework to run test. We use it to make our StepDep data independent. All the test related data stays here
    // We are separating the payload

    public AddPlace addPlace_Payload(String name, String phone_number, String address, String language, String website){

        AddPlace addPlace = new AddPlace();         // we are setting value with the help of Serialization
        addPlace.setAccuracy(55);
        addPlace.setName(name);
        addPlace.setPhone_number(phone_number);
        addPlace.setAddress(address);
        addPlace.setWebsite(website);
        addPlace.setLanguage(language);

        List<String> myList = new ArrayList<>();
        myList.add("Apple");
        myList.add("Ipad Air 5th Gen");

        addPlace.setTypes(myList);

        Location location = new Location();
        location.setLat(-39.39);
        location.setLng(55.59);

        addPlace.setLocation(location);

        return addPlace;
    }

    public String deletePlace_Payload(String placeId){

        return "{\"place_id\":\""+placeId+"\"}";                // use json to string converter and then dynamically send the placeId.
    }
}
