package resourses;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public AddPlace AddPlacePayload(String name,String address, String language){

        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(99);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setName(name);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setWebsite("http://google.com");
        Location location = new Location();
        location.setLat(38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);
        List<String> list = new ArrayList<String>();
        list.add("shoe park");
        list.add("shop");
        addPlace.setTypes(list);
        return addPlace;
    }

    public String deletePlacePayload(String place_id){

        return "{\n" +
                "    \"place_id\":\""+place_id+"\"\n" +
                "}";
    }
}
