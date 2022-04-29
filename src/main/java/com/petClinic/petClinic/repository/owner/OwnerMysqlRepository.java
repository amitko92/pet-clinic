package com.petClinic.petClinic.repository.owner;

import com.petClinic.petClinic.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class OwnerMysqlRepository implements OwnerRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Owner> rowMapper = (rs, rowNum) -> {
        Owner owner = new Owner(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("date_of_birth"),
                rs.getString("registration_date"),
                rs.getString("city"),
                rs.getString("street"),
                rs.getInt("house_number"),
                rs.getInt("apartment"),
                rs.getInt("project_serial_number"));
        return owner;
    };

    @Override
    public Optional<Owner> findOwnerByID(int id) {

        String sqlQuery = "select * from university.owner where id=?";
        List<Owner> owners = null;
        Owner owner = null;
        try{

            owners = jdbcTemplate.query(sqlQuery, new Object[]{id}, new int[]{1}, rowMapper);

            if(owners.size() > 0)
                owner = owners.get(0);

        }catch (DataAccessException e){

            System.out.printf("not founds owner with id= %d%n", id);
        }

        return Optional.ofNullable(owner);
    }

    @Override
    public boolean addOwner(Owner owner) {

        String sqlQuery = "insert into university.owner " +
                "(first_name, last_name, date_of_birth, registration_date, " +
                "city, street, house_number, apartment, project_serial_number) " +
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
            e.printStackTrace();
        }
        return false;
    }
}
