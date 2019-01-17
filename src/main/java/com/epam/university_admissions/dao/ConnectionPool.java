package com.epam.university_admissions.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("resources//database");
    private static final String URL = BUNDLE.getString("url");
    private static final String DRIVER = BUNDLE.getString("driver");
    private static final String PASSWORD = BUNDLE.getString("password");
    private static final String USERNAME = BUNDLE.getString("username");
    private static final int MAX_CONNECTIONS = Integer.parseInt(BUNDLE.getString("max_connections"));


    private static BlockingQueue<Connection> freeConnections;
    private static Set<Connection> usedConnections;

    private ConnectionPool() {
        init();
    }

    private static void init() {
        freeConnections = new ArrayBlockingQueue<>(MAX_CONNECTIONS);
        usedConnections = new HashSet<>(MAX_CONNECTIONS);
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            return;
        }
        try {
            for (int i = 0; i < MAX_CONNECTIONS; i++) {
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                freeConnections.add(connection);
            }
        } catch (SQLException e) {
        }
    }

    public static class ConnectionPoolHolder {
        public static final ConnectionPool HOLDER_INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.HOLDER_INSTANCE;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = freeConnections.poll(2, TimeUnit.SECONDS);
            if (connection == null) {
                connection = freeConnections.poll(7, TimeUnit.SECONDS);
                if (connection == null) {
                    throw new SQLException("No free connection for 7 seconds.");
                }
            }
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void putBack(Connection connection) {
        usedConnections.remove(connection);
        freeConnections.offer(connection);
    }
}
