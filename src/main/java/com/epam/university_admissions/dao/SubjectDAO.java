package com.epam.university_admissions.dao;

import com.epam.university_admissions.entity.Faculty;
import com.epam.university_admissions.entity.Subject;
import com.epam.university_admissions.utils.MysqlRequests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO extends DaoConnection implements Dao<Subject> {
    @Override
    public void create(Subject entity) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.INSERT_SUBJECT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setAutoCommit(connection, false);
            preparedStatement.setString(1, entity.getNameRu());
            preparedStatement.setString(2, entity.getNameEn());
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
    public void update(Subject entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.UPDATE_SUBJECT)) {
            setAutoCommit(connection, false);
            preparedStatement.setString(1, entity.getNameRu());
            preparedStatement.setString(2, entity.getNameEn());
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
    public void delete(Subject entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.DELETE_SUBJECT)) {
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
    public Subject find(int entityKey) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Subject subject = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_SUBJECT)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entityKey);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                subject = getSubject(resultSet);
            }else{
                subject = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return subject;
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> subjectList = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_ALL_SUBJECT)) {
            setAutoCommit(connection, false);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                subjectList.add(getSubject(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return subjectList;
    }

    public List<Subject> findSubjectsInFaculty(Faculty entity){
        List<Subject> facultySubjectsList = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_SUBJECTS_IN_FACULTY)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1,entity.getId());
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                facultySubjectsList.add(getSubject(resultSet));
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

    public Subject findSubjectByNameEn(String subjectName) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Subject subject = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_SUBJECT_BY_NAME_EN)) {
            setAutoCommit(connection, false);
            preparedStatement.setString(1, subjectName);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                subject = getSubject(resultSet);
            }else{
                subject = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return subject;
    }

    public List<Subject> findAllSubjectsNotFaculty(Faculty entity) {
        List<Subject> notFacultySubjectsList = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_ALL_SUBJECTS_NOT_FACULTY)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1,entity.getId());
            resultSet = preparedStatement.executeQuery();
            commit(connection);
            while (resultSet.next()){
                notFacultySubjectsList.add(getSubject(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return notFacultySubjectsList;
    }

    private Subject getSubject(ResultSet resultSet){
        Subject subject = new Subject();
        try {
            subject.setId(resultSet.getInt(1));
            subject.setNameRu(resultSet.getString(2));
            subject.setNameEn(resultSet.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }
}
