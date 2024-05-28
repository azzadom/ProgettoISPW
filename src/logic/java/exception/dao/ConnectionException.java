package exception.dao;

import static exception.dao.TypeDAOException.GENERIC;

public class ConnectionException extends DAOException {

    public ConnectionException() {
        super(GENERIC);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause, GENERIC);
    }
}
