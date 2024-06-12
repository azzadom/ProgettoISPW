package com.privateevents.dao.fs;

import com.privateevents.dao.NotificationDAO;
import com.privateevents.utils.dao.CSVHandler;
import com.privateevents.exception.dao.DAOException;
import com.privateevents.model.Notification;
import com.privateevents.model.TypeNotif;

import static com.privateevents.exception.dao.TypeDAOException.*;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NotificationFS implements NotificationDAO {

    private static final String FILE_PATH = "src/main/resources/data/Notification.csv";

    @Override
    public List<Notification> selectNotifications(String idOrganizer) throws DAOException {
        try{
            CSVHandler handler = new CSVHandler(FILE_PATH, ",");
            List<String[]> foundrs = handler.find(r -> r[4].equals(idOrganizer));
            return foundrs.stream().map(this::fromCsvRecord).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new DAOException("Error in selectNotifications: " + e.getMessage(), e.getCause(), GENERIC);
        }
    }

    @Override
    public void addNotification(String idOrganizer, Notification notification) throws DAOException {
         try {
            CSVHandler handler = new CSVHandler(FILE_PATH, ",");
            if(!(handler.find(uniqueKey(idOrganizer, notification.getDateAndTime().toString(),
                    notification.getEventName(), notification.getBookingCode())).isEmpty())) {
                throw new DAOException("Notification already exists", DUPLICATE);
            }
            List<String[]> rs = new ArrayList<>();
            rs.add(toCsvRecord(idOrganizer, notification));
            handler.writeAll(rs);
        } catch (IOException e) {
            throw new DAOException("Error in addNotification: " + e.getMessage(), e.getCause(), GENERIC);
         }
    }

    @Override
    public void deleteNotification(String idOrganizer, List<Notification> notification) throws DAOException {
        try{
            CSVHandler handler = new CSVHandler(FILE_PATH, ",");
            List<Predicate<String[]>> predicates = new ArrayList<>();
            for (Notification n : notification) {
                predicates.add(uniqueKey(idOrganizer, n.getDateAndTime().toString(), n.getEventName(), n.getBookingCode()));
            }
            handler.remove(predicates);
        } catch (IOException e) {
            throw new DAOException("Error in deleteNotification: " + e.getMessage(), e.getCause(), GENERIC);
        }
    }

    public void deleteNotificationByOrg(String idOrganizer) throws DAOException {
        try{
            CSVHandler handler = new CSVHandler(FILE_PATH, ",");
            handler.remove(n -> n[4].equals(idOrganizer));
        } catch (IOException e) {
            throw new DAOException("Error in deleteNotification: " + e.getMessage(), e.getCause(), GENERIC);
        }
    }

    private Predicate<String[]> uniqueKey(String organizer, String datetime, String event, String code) {
        return r -> r[1].equals(datetime) && r[2].equals(event) && r[3].equals(code) && r[4].equals(organizer);
    }

    private Notification fromCsvRecord(String[] r) {
        return new Notification(TypeNotif.valueOf(r[0]), LocalDateTime.parse(r[1]), r[2], r[3]);
    }

    private String[] toCsvRecord(String idOrganizer, Notification notification) {
        return new String[]{String.valueOf(notification.getType()), notification.getDateAndTime().toString(), notification.getEventName(), notification.getBookingCode(),
                idOrganizer};
    }
}
