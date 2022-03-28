package pojo;

public enum API_Resources {
    // enum is the special class in java - collection of constants or methods

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI(" /maps/api/place/get/json"),
    PutPlaceAPI("/maps/api/place/update/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");

    private String resource;

    API_Resources(String resource) {
        // creating constructor is mandatory for enum to pass the args
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }

}
