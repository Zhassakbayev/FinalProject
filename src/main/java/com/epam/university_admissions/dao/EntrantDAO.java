package com.epam.university_admissions.dao;

import com.epam.university_admissions.entity.Entrant;
import com.epam.university_admissions.entity.User;
import com.epam.university_admissions.utils.ConstantFields;
import com.epam.university_admissions.utils.ConstantFields.*;
import com.epam.university_admissions.utils.MysqlRequests;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EntrantDAO extends DaoConnection implements Dao<Entrant> {

    @Override
    public void create(Entrant entity) {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement =connection.prepareStatement(MysqlRequests.INSERT_ENTRANT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setAutoCommit(connection,false);
            preparedStatement.setInt(2,entity.getIin());
            preparedStatement.setString(3,entity.getCity());
            preparedStatement.setString(4,entity.getDistrict());
            preparedStatement.setString(5,entity.getSchoolName());
            preparedStatement.setInt(6,entity.getUserId());
            preparedStatement.setBoolean(7,entity.getBlockedStatus());
            preparedStatement.executeUpdate();
            commit(connection);
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                entity.setId(resultSet.getInt(1));
            }
        } catch(SQLException e){
            e.printStackTrace();
            rollback(connection);
        }finally {
            close(resultSet);
            close(connection);
        }


    }

    @Override
    public void update(Entrant entity) {

    }

    @Override
    public void delete(Entrant entity) {

    }

    @Override
    public Entrant find(int entityKey) {
        return null;
    }

    @Override
    public List<Entrant> findAll() {
        return null;
    }

}
