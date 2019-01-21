package com.epam.university_admissions.dao;

import com.epam.university_admissions.entity.Entrant;
import com.epam.university_admissions.entity.Mark;
import com.epam.university_admissions.entity.Subject;
import com.epam.university_admissions.utils.MysqlRequests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarkDAO extends DaoConnection implements Dao<Mark> {
    @Override
    public void create(Mark entity) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.INSERT_MARK, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entity.getEntrantId());
            preparedStatement.setInt(2, entity.getSubjectId());
            preparedStatement.setInt(3, entity.getMark());
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
    public void update(Mark entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.UPDATE_MARK)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entity.getEntrantId());
            preparedStatement.setInt(2, entity.getSubjectId());
            preparedStatement.setInt(3, entity.getMark());
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
    public void delete(Mark entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.DELETE_MARK)) {
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
    public Mark find(int entityKey) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Mark mark = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_MARK)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entityKey);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                mark = getMark(resultSet);
            }else{
                mark = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return mark;
    }

    @Override
    public List<Mark> findAll() {
        List<Mark> markList = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_ALL_MARK)) {
            setAutoCommit(connection, false);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                markList.add(getMark(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return markList;
    }

    public Mark findMarkByEntrantSubject(Entrant entrant , Subject subject){
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Mark mark = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_MARK_BY_ENTRANT_SUBJECT)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entrant.getId());
            preparedStatement.setInt(2,subject.getId());
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                mark = getMark(resultSet);
            }else{
                mark = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return mark;
    }

    private Mark getMark(ResultSet resultSet){
        Mark mark = new Mark();
        try {
            mark.setId(resultSet.getInt(1));
            mark.setEntrantId(resultSet.getInt(2));
            mark.setSubjectId(resultSet.getInt(3));
            mark.setMark(resultSet.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mark;
    }
}
