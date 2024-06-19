package com.privateevents.dao.fs;

import com.privateevents.dao.TicketDAO;
import com.privateevents.utils.dao.CSVHandler;
import com.privateevents.exception.dao.DAOException;
import com.privateevents.model.Ticket;

import static com.privateevents.exception.dao.TypeDAOException.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class TicketFS implements TicketDAO {

    private static final String FILE_PATH = "src/main/resources/data/Ticket.csv";

    @Override
    public List<Ticket> selectTickets(Integer idEvent) throws DAOException {
        try {
            CSVHandler handler = new CSVHandler(FILE_PATH, ",");
            List<String[]> foundRecord = handler.find(r -> r[5].equals(String.valueOf(idEvent)));
            return foundRecord.stream().map(this::fromCvsRecord).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new DAOException("Error in selectTickets: " + e.getMessage(), e, GENERIC);
        }
    }

    private Predicate<String[]> uniqueKey(String idEvent, String typeName) {
        return r -> r[0].equals(typeName) && r[5].equals(String.valueOf(idEvent));
    }

    private Ticket fromCvsRecord(String[] r) {
        return new Ticket(r[0], Double.parseDouble(r[1]),
                Integer.parseInt(r[2]), r[3],
                Integer.parseInt(r[4]));
    }

    private String[] toCvsRecord(Ticket ticket, Integer idEvent) {
        return new String[]{ticket.getType(), String.valueOf(ticket.getPrice()),
                String.valueOf(ticket.restriction()), ticket.getDescription(),
                String.valueOf(ticket.getLimit()), String.valueOf(idEvent)};
    }

}
