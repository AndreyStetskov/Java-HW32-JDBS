package org.crazyemperor.connections.DBConnection;

import org.crazyemperor.connections.ConnectionToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDBCar implements ConnectionToDB {

    private final String URL;
    private final String USER;
    private final String PASSWORD;


    public ConnectionToDBCar(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    @Override
    public Connection getConnect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (
                SQLException e) {
            throw new RuntimeException("Connection denied", e);
        }
    }
}
