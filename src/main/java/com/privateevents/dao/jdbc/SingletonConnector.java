package com.privateevents.dao.jdbc;

import com.privateevents.exception.dao.ConnectionException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingletonConnector {
    protected static SingletonConnector instance = null;
    private Connection connection = null;
    private static final String CONNECTION_SETTINGS = "src/main/resources/properties/db.properties";

    protected SingletonConnector() {}

    public static synchronized SingletonConnector getConnector() {
        if (instance == null) {
            instance = new SingletonConnector();
        }
        return instance;
    }

    public Connection getConnection() throws ConnectionException {
        if (connection == null) {
            try (InputStream input = new FileInputStream(CONNECTION_SETTINGS)) {
                Properties properties = new Properties();
                properties.load(input);
                input.close();

                String connectionUrl = properties.getProperty("CONNECTION_URL");
                String user = properties.getProperty("USER");
                String pass = properties.getProperty("PASS");

                connection = DriverManager.getConnection(connectionUrl, user, pass);
            } catch (IOException | SQLException e) {
                throw new ConnectionException("Error in getConnection: " + e.getMessage(), e.getCause());
            }
        }
        return connection;
    }

    public void endConnection() throws ConnectionException {
        if (connection != null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new ConnectionException("Error in endConnection: " + e.getMessage(), e.getCause());
            }
        }
    }

}
