package pojo_deserialized;

import java.util.List;

public class Courses {          // this is child | sub json of GetCourses

    private List<WebAutomation> webAutomation;      // since it is returning a list of array, we might not know the actual number of index in array so return as List
    private List<Api> api;
    private List<Mobile> mobile;

    public List<WebAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<WebAutomation>  webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<Api>  getApi() {
        return api;
    }

    public void setApi(List<Api>  api) {
        this.api = api;
    }

    public List<Mobile> getMobile() {
        return mobile;
    }

    public void setMobile(List<Mobile> mobile) {
        this.mobile = mobile;
    }
}

