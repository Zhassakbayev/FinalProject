package com.epam.university_admissions.dao;

import java.sql.Connection;

public abstract class DaoConnection {

    protected ConnectionPool connectionPool;

    protected Connection getConnection(){
        return connectionPool.getConnection();
    }

    protected void close(Connection connection){
        connectionPool.putBack(connection);
    }


}
