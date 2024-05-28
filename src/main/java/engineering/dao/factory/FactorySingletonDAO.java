package engineering.dao.factory;

import dao.*;

public abstract class FactorySingletonDAO {

    protected static FactorySingletonDAO instance = null;

    protected FactorySingletonDAO(){}

    public static synchronized FactorySingletonDAO getDefaultDAO (){
        if (instance == null) {
            String daoType = System.getenv("DAO_TYPE");
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
