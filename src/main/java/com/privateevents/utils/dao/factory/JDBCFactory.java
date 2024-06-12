package com.privateevents.utils.dao.factory;

import com.privateevents.dao.*;
import com.privateevents.dao.jdbc.*;

public class JDBCFactory extends FactorySingletonDAO{

        public BookingDAO getBookingDAO(){
            return new BookingJDBC();
        }

        public EventDAO getEventDAO(){
            return new EventJDBC();
        }

        public TicketDAO getTicketDAO(){
            return new TicketJDBC();
        }

        public OrganizerDAO getOrganizerDAO(){
            return new OrganizerJDBC();
        }

        public NotificationDAO getNotificationDAO(){
            return new NotificationJDBC();
        }
}
