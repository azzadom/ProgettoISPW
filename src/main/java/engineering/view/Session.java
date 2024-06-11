package engineering.view;

import bean.EventBean;
import bean.UserBean;

public class Session {

    private UserBean user;
    private String city;
    private EventBean event;

    public void setUser(UserBean user) {
        this.user = user;
    }

    public UserBean getUser() {
        return user;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setEvent(EventBean event) {
        this.event = event;
    }

    public EventBean getEvent() {
        return event;
    }

    public void reset() {
        this.user = null;
        this.city = null;
        this.event = null;
    }

    public void softReset() {
        this.city = null;
        this.event = null;
    }

    public void resetEvent() {
        this.event = null;
    }

    public void resetCity() {
        this.city = null;
    }
}
