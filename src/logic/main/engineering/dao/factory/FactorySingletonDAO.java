package engineering.dao.factory;

import dao.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class FactorySingletonDAO {

    protected static FactorySingletonDAO instance = null;

    protected FactorySingletonDAO(){}

    public static synchronized FactorySingletonDAO getDefaultDAO (){
        if (instance == null) {
            String daoType = System.getenv("DAO_TYPE"); //ENV -> DAO_TYPE=FS; or DAO_TYPE=JDBC;
            switch (TypeDAO.valueOf(daoType)) {
                case JDBC:
                    instance = new JDBCFactory();
                    break;
                case FS:
                    instance = new FSFactory();
                    break;
            }
            return instance;
        }
        return instance;
    }

    public abstract BookingDAO getBookingDAO();

    public abstract EventDAO getEventDAO();

    public abstract TicketDAO getTicketDAO();

    public abstract OrganizerDAO getOrganizerDAO();

    public abstract NotificationDAO getNotificationDAO();

}
