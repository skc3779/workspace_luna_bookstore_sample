package com.bookstore.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String connectionString;
    private String driverName;
    private String username;
    private String password;

    public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class.forName(this.driverName).newInstance();
        Connection conn = DriverManager.getConnection (this.connectionString, this.username, this.password);
        return conn;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
