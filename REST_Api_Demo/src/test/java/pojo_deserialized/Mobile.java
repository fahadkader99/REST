package pojo_deserialized;

public class Mobile {       // child of Courses & grand child for GetCourses

    private String courseTitle;
    private int price;

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
