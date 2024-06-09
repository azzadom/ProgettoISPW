package engineering;

import bean.UserBean;

import java.time.ZoneId;

public class Session {

    private UserBean user;
    private ZoneId defaultZone;
    private Boolean returningHome;


    public Session() {
        this.defaultZone = ZoneId.systemDefault();
        this.returningHome = false;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public UserBean getUser() {
        return user;
    }

    public ZoneId getDefaultZone() {
        return defaultZone;
    }

    public void setReturningHome(Boolean returningHome) {
        this.returningHome = returningHome;
    }

    public Boolean getReturningHome() {
        return returningHome;
    }
}
