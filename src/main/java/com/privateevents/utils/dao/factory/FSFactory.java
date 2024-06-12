package com.privateevents.utils.dao.factory;

import com.privateevents.dao.*;
import com.privateevents.dao.fs.*;

public class FSFactory extends FactorySingletonDAO {

    public BookingDAO getBookingDAO(){
        return new BookingFS();
    }

    public EventDAO getEventDAO(){
        return new EventFS();
    }

    public TicketDAO getTicketDAO(){
        return new TicketFS();
    }

    public OrganizerDAO getOrganizerDAO(){
        return new OrganizerFS();
    }

    public NotificationDAO getNotificationDAO(){
        return new NotificationFS();
    }
}
