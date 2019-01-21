package com.epam.university_admissions.dao;

import com.epam.university_admissions.entity.Faculty;
import com.epam.university_admissions.utils.MysqlRequests;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyDAO extends DaoConnection implements Dao<Faculty> {
    @Override
    public void create(Faculty entity) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.INSERT_FACULTY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setAutoCommit(connection, false);
            preparedStatement.setString(1, entity.getNameRu());
            preparedStatement.setString(2, entity.getNameEn());
            preparedStatement.setInt(3, entity.getTotalSeats());
            preparedStatement.setInt(4, entity.getBudgetSeats());
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
    public void update(Faculty entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.UPDATE_FACULTY)) {
            setAutoCommit(connection, false);
            preparedStatement.setString(1, entity.getNameRu());
            preparedStatement.setString(2, entity.getNameEn());
            preparedStatement.setInt(3, entity.getTotalSeats());
            preparedStatement.setInt(4, entity.getBudgetSeats());
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
    public void delete(Faculty entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.DELETE_FACULTY)) {
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
    public Faculty find(int entityKey) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Faculty faculty = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_FACULTY)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entityKey);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                faculty = getFaculty(resultSet);
            }else{
                faculty = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return faculty;
    }

    @Override
    public List<Faculty> findAll() {
        List<Faculty> facultyList = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_ALL_FACULTY)) {
            setAutoCommit(connection, false);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                facultyList.add(getFaculty(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return facultyList;
    }

    public Faculty findFacultyByName(String name){
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Faculty faculty = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_FACULTY_BY_NAME)) {
            setAutoCommit(connection, false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,name);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                faculty = getFaculty(resultSet);
            }else{
                faculty = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return faculty;
    }

    private Faculty getFaculty(ResultSet resultSet){
        Faculty faculty = new Faculty();
        try {
            faculty.setId(resultSet.getInt(1));
            faculty.setNameRu(resultSet.getString(2));
            faculty.setNameEn(resultSet.getString(3));
            faculty.setTotalSeats(resultSet.getInt(4));
            faculty.setBudgetSeats(resultSet.getInt(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculty;
    }
}
