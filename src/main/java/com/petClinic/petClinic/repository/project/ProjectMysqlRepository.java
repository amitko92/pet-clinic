package com.petClinic.petClinic.repository.project;

import com.petClinic.petClinic.entity.Project;
import com.petClinic.petClinic.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProjectMysqlRepository implements ProjectRepository{

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
    public Optional<Project> getProjectByProjectNumber(int serialNumber) {
        String sqlQuery = "select * " +
                "from projects " +
                "where serialNumber=?";
        Project project = null;
        List<Project> list = null;
        try{

            list = jdbcTemplate.query(sqlQuery, new Object[]{serialNumber}, new int[]{1}, rowMapper);

            if(list != null || list.size() > 0){
                project = list.get(0);
            }
        }catch (DataAccessException e){

            System.out.printf("not founds project with serialNumber= %d%n", serialNumber);
        }

        return Optional.ofNullable(project);
    }

    @Override
    public Optional<List<Project>> getAllProjects() {
        String sqlQuery = "select * from projects";
        Project project = null;
        List<Project> list = null;
        try{

            list = jdbcTemplate.query(sqlQuery, rowMapper);
        }catch (DataAccessException e){

            System.out.printf("not founds projects");
        }

        return Optional.ofNullable(list);
    }

}
