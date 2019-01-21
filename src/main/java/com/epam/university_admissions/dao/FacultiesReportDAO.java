package com.epam.university_admissions.dao;

import com.epam.university_admissions.entity.FacultiesReport;
import com.epam.university_admissions.entity.Faculty;
import com.epam.university_admissions.utils.MysqlRequests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultiesReportDAO extends DaoConnection{

    public List<FacultiesReport> getFacultiesReportList(Faculty faculty){
        List<FacultiesReport> facultiesReportList = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(MysqlRequests.FACULTY_REPORT)) {
            setAutoCommit(connection, false);
            preparedStatement.setInt(1,faculty.getId());
            commit(connection);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                facultiesReportList.add(getFacultiesReport(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            setAutoCommit(connection, true);
            close(resultSet);
            close(connection);
        }
        return facultiesReportList;
    }

    private FacultiesReport getFacultiesReport(ResultSet resultSet){
        FacultiesReport report = new FacultiesReport();
        try {
            report.setFacultyId(resultSet.getInt(1));
            report.setFacultyNameRu(resultSet.getString(2));
            report.setFacultyNameEn(resultSet.getString(3));
            report.setIin(resultSet.getString(4));
            report.setFirstName(resultSet.getString(5));
            report.setLastName(resultSet.getString(6));
            report.setSecondName(resultSet.getString(7));
            report.setTotalPointScore(resultSet.getInt(8));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }
}
