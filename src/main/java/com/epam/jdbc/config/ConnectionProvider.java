package com.epam.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employees";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "hayastanarm93";
    private static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    private volatile static Connection instance;

    static {
        try {
            Class.forName(JDBC_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        try {
            if (instance == null || instance.isClosed()) {
                synchronized (ConnectionProvider.class) {
                    if (instance == null) {
                        try {
                            instance = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
