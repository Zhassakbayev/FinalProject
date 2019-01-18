package com.epam.university_admissions.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DaoConnection{

    protected ConnectionPool CONNECTION_POOL  = ConnectionPool.getInstance();

    protected Connection getConnection(){
        return CONNECTION_POOL.getConnection();
    }

    protected void close(Connection connection){
        CONNECTION_POOL.putBack(connection);
    }

    protected void close(ResultSet resultSet){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void rollback (Connection connection){
        if (connection!=null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void setAutoCommit(Connection connection, boolean autoCommit){
        if (connection != null){
            try {
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void commit (Connection connection){
        if(connection!=null){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
