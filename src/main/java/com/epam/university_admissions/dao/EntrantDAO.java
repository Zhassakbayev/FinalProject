package com.epam.university_admissions.dao;

import com.epam.university_admissions.entity.Entrant;
import com.epam.university_admissions.entity.Faculty;
import com.epam.university_admissions.entity.User;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.ConstantFields.*;
import com.epam.university_admissions.utils.MysqlRequests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntrantDAO extends DaoConnection implements Dao<Entrant> {

    @Override
    public void create(Entrant entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.INSERT_ENTRANT)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1,entity.getId());
            preparedStatement.setString(2, entity.getIin());
            preparedStatement.setString(3, entity.getCity());
            preparedStatement.setString(4, entity.getDistrict());
            preparedStatement.setString(5, entity.getSchoolName());
            preparedStatement.setBoolean(6,entity.getBlockedStatus());
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
    public void update(Entrant entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.UPDATE_ENTRANT)) {
            setAutoCommit(connection, false);
            preparedStatement.setString(1, entity.getIin());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getDistrict());
            preparedStatement.setString(4, entity.getSchoolName());
            preparedStatement.setBoolean(5, entity.getBlockedStatus());
            preparedStatement.setInt(6, entity.getId());
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
    public void delete(Entrant entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.DELETE_ENTRANT)) {
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
    public Entrant find(int entityKey) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Entrant entrant = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_ENTRANT)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entityKey);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                entrant = getEntrant(resultSet);
            }else{
                entrant = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return entrant;
    }

    @Override
    public List<Entrant> findAll() {
        List<Entrant> entrantList = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_ALL_ENTRANTS)) {
            setAutoCommit(connection, false);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                entrantList.add(getEntrant(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return entrantList;
    }

    public List<Entrant> findAllEntrantInFaculty(Faculty faculty){
        List<Entrant> facultyEntrantList = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_ALL_ENTRANTS_IN_FACULTY)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1,faculty.getId());
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                facultyEntrantList.add(getEntrant(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return facultyEntrantList;
    }

    public Entrant findEntrantByUserId(User user){
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Entrant entrant = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_ENTRANT_BY_USER_ID)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, user.getId());
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                entrant = getEntrant(resultSet);
            }else{
                entrant = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return entrant;
    }

    private Entrant getEntrant(ResultSet resultSet){
        Entrant entrant = new Entrant();
        try {
            entrant.setId(resultSet.getInt(1));
            entrant.setIin(resultSet.getString(2));
            entrant.setCity(resultSet.getString(3));
            entrant.setDistrict(resultSet.getString(4));
            entrant.setSchoolName(resultSet.getString(5));
            entrant.setBlockedStatus(resultSet.getBoolean(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrant;
    }
}
