package test_Data;

import pojo.Add_Place;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class Test_Data_Build {

    public Add_Place addPlace_Payload( String name, String address, String language, String website) {         // passing data driven from Scenario outline

        // creating object for Serialization of the class || need Setter for Serialization
        Add_Place addPlace = new Add_Place();

        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setPhone_number("3473473478");
        addPlace.setAddress(address);
        addPlace.setWebsite(website);
        addPlace.setLanguage(language);

        // Json with array / List
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        addPlace.setTypes(myList);                                                                                            // to add value to the List we have to create a list add value and pass that list

        // Json with sub json + subclass - need to create subclass object first
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        addPlace.setLocation(location);                                                                                       // after setting values to our subclass object we just pass the class to the setLocation.

        /* Now I have created java object by passing all the values of Json with supported POJO class. (above) */

        return addPlace;

    }
}
