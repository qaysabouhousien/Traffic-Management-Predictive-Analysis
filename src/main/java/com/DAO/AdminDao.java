package com.DAO;

import com.Entity.Admin;
import com.Entity.ProgramManger;
import com.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class AdminDao implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Collection<User> getUsers() {

        final String query = "SELECT id,user_name ,password,registration_date " +
                "FROM admin_user ";

        try {
            return jdbcTemplate.query(query, (resultSet, i) -> getUser(resultSet));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public User getUserById(int id) {

        final String query = "Select * FROM admin_user WHERE id = ? LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(query, (resultSet, i) -> getUser(resultSet), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getUser(ResultSet resultSet) throws SQLException {
        User user = new Admin();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password"));
        user.setRegistration_date(resultSet.getDate("registration_date"));
        return user;
    }

    public int addAdmin(User user) {

        final String query = "INSERT INTO admin_user(user_name,password) VALUES(?,?)";

        return jdbcTemplate.update(query, preparedStatement ->
                {
                    preparedStatement.setString(1, user.getName());
                    preparedStatement.setString(2, user.getPassword());
                }
        );
    }

    public int addManger(User user) {

        final String query = "INSERT INTO program_manger(user_name,password,registration_by) VALUES(?,?,?)";

        ProgramManger programManger = (ProgramManger) user;


        return jdbcTemplate.update(query, preparedStatement ->
        {
            preparedStatement.setString(1, programManger.getName());
            preparedStatement.setString(2, programManger.getPassword());
            preparedStatement.setInt(3, programManger.getRegistrationBy());
        });
    }

    @Override
    public User getUserByName(String name) {

        final String query = "SELECT * FROM admin_user where user_name = ? LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(query, (resultSet, i) -> getUser(resultSet), name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }




    public Collection<User> getMangers() {
        final String query = "SELECT * FROM program_manger";
        return jdbcTemplate.query(query, ((resultSet, i) -> (getUser(resultSet))));
    }

}