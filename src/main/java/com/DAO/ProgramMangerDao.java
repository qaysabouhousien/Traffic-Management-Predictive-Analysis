package com.DAO;

import com.Entity.ProgramManger;
import com.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * A repo for handling Program Manager DB operations
 * @author - Qays
 */
@Repository
public class ProgramMangerDao implements UserDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Gets all Program Manager users
     * @return a collection of {@link User}
     */
    @Override
    public Collection<User> getUsers() {
        final String query = "SELECT * FROM program_manger";
        return jdbcTemplate.query(query,((resultSet, i) -> (getUser(resultSet))));
    }

    /**
     * Gets a program manager by id
     * @param id program Manager ID
     * @return a {@link User}
     */
    @Override
    public User getUserById(int id) {
        final String query = "SELECT * FROM program_manger WHERE id =? LIMIT 1";

        try{
            return jdbcTemplate.queryForObject(query,(resultSet, i) -> getUser(resultSet),id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    /**
     * Extract the result from the result set and Builds a program from it.
     * @param resultSet Sql ResultSet
     * @return extracted {@link User}
     * @throws SQLException fetching results from resultSet throws an SQLException.
     */
    @Override
    public User getUser(ResultSet resultSet) throws SQLException {
        ProgramManger user = new ProgramManger();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password"));
        user.setRegistration_date(resultSet.getDate("registration_date"));
        return user;
    }


    /**
     * gets admin name and checks if program_manager table contains such user name
     * @param name userName
     * @return a {@link User}
     */
    @Override
    public User getUserByName(String name) {
        final String query = "SELECT * FROM program_manger WHERE user_name= ? LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(query, (resultSet, i) -> getUser(resultSet), name);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }


}
