package engineering;

import bean.UserBean;

import java.time.ZoneId;

public class Session {

    private UserBean user;
    private Integer view;
    private ZoneId defaultZone;

    public Session(Integer view) {
        this.view = view;
        this.defaultZone = ZoneId.systemDefault();
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public UserBean getUser() {
        return user;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getView() {
        return view;
    }

    public ZoneId getDefaultZone() {
        return defaultZone;
    }
}
