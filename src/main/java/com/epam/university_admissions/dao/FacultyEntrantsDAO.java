package com.epam.university_admissions.dao;

import com.epam.university_admissions.entity.Entrant;
import com.epam.university_admissions.entity.Faculty;
import com.epam.university_admissions.entity.FacultyEntrants;
import com.epam.university_admissions.utils.MysqlRequests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyEntrantsDAO extends DaoConnection implements Dao<FacultyEntrants> {
    @Override
    public void create(FacultyEntrants entity) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.INSERT_FACULTY_ENTRANTS, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entity.getEntrantId());
            preparedStatement.setInt(2, entity.getFacultyId());
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
    public void update(FacultyEntrants entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.UPDATE_FACULTY_ENTRANTS)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entity.getEntrantId());
            preparedStatement.setInt(2, entity.getFacultyId());
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
    public void delete(FacultyEntrants entity) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.DELETE_FACULTY_ENTRANTS)) {
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
    public FacultyEntrants find(int entityKey) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        FacultyEntrants facultyEntrants = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_FACULTY_ENTRANTS)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entityKey);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                facultyEntrants = getFacultyEntrants(resultSet);
            }else{
                facultyEntrants = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return facultyEntrants;
    }

    @Override
    public List<FacultyEntrants> findAll() {
        List<FacultyEntrants> facultyEntrantsList = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_ALL_FACULTY_ENTRANTS)) {
            setAutoCommit(connection, false);
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                facultyEntrantsList.add(getFacultyEntrants(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return facultyEntrantsList;
    }

    public FacultyEntrants findFacultyEntrantsByEntrant(Entrant entrant){
        Connection connection = getConnection();
        ResultSet resultSet = null;
        FacultyEntrants facultyEntrants = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_FACULTY_ENTRANTS_BY_ENTRANT)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, entrant.getId());
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                facultyEntrants = getFacultyEntrants(resultSet);
            }else{
                facultyEntrants = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return facultyEntrants;
    }

    public FacultyEntrants findFacultyEntrantsByFaculty(Faculty faculty){
        Connection connection = getConnection();
        ResultSet resultSet = null;
        FacultyEntrants facultyEntrants = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FIND_FACULTY_ENTRANTS_BY_FACULTY)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1, faculty.getId());
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                facultyEntrants = getFacultyEntrants(resultSet);
            }else{
                facultyEntrants = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return facultyEntrants;
    }

    private FacultyEntrants getFacultyEntrants(ResultSet resultSet){
        FacultyEntrants facultyEntrants = new FacultyEntrants();
        try {
            facultyEntrants.setId(resultSet.getInt(1));
            facultyEntrants.setEntrantId(resultSet.getInt(2));
            facultyEntrants.setFacultyId(resultSet.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultyEntrants;
    }
}
