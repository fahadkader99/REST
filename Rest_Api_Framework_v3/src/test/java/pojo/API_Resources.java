package pojo;

public enum API_Resources {
    // Enum is Special class in java which has collection of constants or methods.

    // We are using Enum to manage our resource.

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");


    private String resource;

    API_Resources(String resource){
        this.resource = resource;
    }
    public String getResource(){
        return resource;
    }

}
