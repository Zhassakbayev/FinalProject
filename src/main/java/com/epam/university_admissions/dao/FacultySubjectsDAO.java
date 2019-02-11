package com.epam.university_admissions.dao;

import com.epam.university_admissions.entity.Faculty;
import com.epam.university_admissions.entity.FacultySubjects;
import com.epam.university_admissions.utils.MysqlRequests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultySubjectsDAO extends DaoConnection implements Dao<FacultySubjects> {
    @Override
    public void create(FacultySubjects entity) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.INSERT_FACULTY_SUBJECTS, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entity.getFacultyId());
            preparedStatement.setInt(2, entity.getSubjectId());
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
    public void update(FacultySubjects entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.UPDATE_FACULTY_SUBJECTS)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entity.getFacultyId());
            preparedStatement.setInt(2, entity.getSubjectId());
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
    public void delete(FacultySubjects entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.DELETE_FACULTY_SUBJECTS)) {
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
    public FacultySubjects find(int entityKey) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        FacultySubjects facultySubjects = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_FACULTY_SUBJECTS)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entityKey);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                facultySubjects = getFacultySubjects(resultSet);
            }else{
                facultySubjects = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return facultySubjects;
    }

    @Override
    public List<FacultySubjects> findAll() {
        List<FacultySubjects> facultySubjectsList = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_ALL_FACULTY_SUBJECTS)) {
            setAutoCommit(connection, false);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                facultySubjectsList.add(getFacultySubjects(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return facultySubjectsList;
    }

    public void deleteAllSubjectFaculty(Faculty entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.DELETE_ALL_SUBJECTS_FACULTY)) {
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

    private FacultySubjects getFacultySubjects(ResultSet resultSet){
        FacultySubjects facultySubjects = new FacultySubjects();
        try {
            facultySubjects.setId(resultSet.getInt(1));
            facultySubjects.setFacultyId(resultSet.getInt(2));
            facultySubjects.setSubjectId(resultSet.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultySubjects;
    }
}
