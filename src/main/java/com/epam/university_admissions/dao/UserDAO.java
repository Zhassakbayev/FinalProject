package com.epam.university_admissions.dao;

import com.epam.university_admissions.entity.User;
import com.epam.university_admissions.utils.MysqlRequests;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DaoConnection implements Dao<User> {
    @Override
    public void create(User entity) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setAutoCommit(connection, false);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getSecondName());
            preparedStatement.setDate(4, Date.valueOf(entity.getDateOfBirth()));
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getPassword());
            preparedStatement.setString(7, entity.getRole());
            preparedStatement.setString(8,entity.getLanguage());
            preparedStatement.executeUpdate();
            commit(connection);
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
    }

    @Override
    public void update(User entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.UPDATE_USER)) {
            setAutoCommit(connection, false);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getSecondName());
            preparedStatement.setDate(4, Date.valueOf(entity.getDateOfBirth()));
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getPassword());
            preparedStatement.setString(7, entity.getRole());
            preparedStatement.setString(8, entity.getLanguage());
            preparedStatement.setInt(9, entity.getId());
            preparedStatement.executeUpdate();
            commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(connection);
        }
    }

    @Override
    public void delete(User entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.DELETE_USER)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
            commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(connection);
        }
    }

    @Override
    public User find(int entityKey) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_USER)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entityKey);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = getUser(resultSet);
            }else{
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_ALL_USER)) {
            setAutoCommit(connection, false);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                userList.add(getUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return userList;
    }

    public User findUserByEmailPassword(String email,String password){
        Connection connection = getConnection();
        ResultSet resultSet = null;
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_USER_BY_EMAIL_PASSWORD)) {
            setAutoCommit(connection, false);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2,password);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = getUser(resultSet);
            }else{
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return user;
    }

    public User findUserByEmail(String email){
        Connection connection = getConnection();
        ResultSet resultSet = null;
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_USER_BY_EMAIL)) {
            setAutoCommit(connection, false);
            preparedStatement.setString(1, email);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = getUser(resultSet);
            }else{
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return user;
    }

    private User getUser(ResultSet resultSet){
        User user = new User();
        try {
            user.setId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setSecondName(resultSet.getString(4));
            user.setDateOfBirth(resultSet.getDate(5).toString());
            user.setEmail(resultSet.getString(6));
            user.setPassword(resultSet.getString(7));
            user.setRole(resultSet.getString(8));
            user.setLanguage(resultSet.getString(9));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
