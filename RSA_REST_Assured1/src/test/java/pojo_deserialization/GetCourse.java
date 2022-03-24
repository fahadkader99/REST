package pojo_deserialization;

public class GetCourse {                    // Grandparent of all class && Parent for Courses class

    private String url;
    private String services;
    private String expertise;
    private	Courses Courses;                                        // it has sub-Json, so we have sub-class
    private String instructor;
    private String linkedIn;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public pojo_deserialization.Courses getCourses() {                              // return type has to be package name.classname
        return Courses;
    }

    public void setCourses(pojo_deserialization.Courses courses) {
        Courses = courses;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }
}
