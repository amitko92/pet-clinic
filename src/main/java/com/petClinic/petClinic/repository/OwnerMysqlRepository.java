package com.petClinic.petClinic.repository;

import com.petClinic.petClinic.entity.Owner;
import com.petClinic.petClinic.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OwnerMysqlRepository implements OwnerRepository{

    RowMapper<Project> rowMapper = (rs, rowNum) -> {
        Project project = new Project(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("serialNumber"));
        return project;
    };

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Owner findOwnerByID(int id) {
        return null;
    }

    @Override
    public boolean addOwner(Owner owner) {

        String sqlQuery = "insert into university.owner " +
                "set(first_name, last_name, date_of_birth, registration_date, " +
                "city, street, house_number, apartment, project_serial_number)" +
                "values (?,?,?,?,?,?,?,?,?)";

        PreparedStatementCallback callback = new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

                ps.setString(1,owner.getfName());
                ps.setString(2,owner.getlName());
                ps.setString(3,owner.getDateOfBirth());
                ps.setString(4,owner.getRegistrationDate());
                ps.setString(5,owner.getCity());
                ps.setString(6,owner.getStreet());
                ps.setInt(7,owner.getHouse());
                ps.setInt(8,owner.getApartment());
                ps.setInt(9,owner.getProjectSerialNumber());
                return ps.execute();
            }
        };

        try{

            return (boolean) jdbcTemplate.execute(sqlQuery,callback);
        }catch (DataAccessException e){

            System.out.printf("failed to add owner ", owner.toString());
        }
        return false;
    }
}