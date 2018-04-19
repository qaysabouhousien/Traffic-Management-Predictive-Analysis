package com.DAO;

import com.Entity.Admin;
import com.Entity.ProgramManger;
import com.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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

        final String query = "INSERT INTO program_manger(user_name,password) VALUES(?,?)";

        ProgramManger programManger = (ProgramManger) user;


        return jdbcTemplate.update(query, preparedStatement ->
        {
            preparedStatement.setString(1, programManger.getName());
            preparedStatement.setString(2, programManger.getPassword());
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
        return jdbcTemplate.query(query, ((resultSet, i) -> (getManger(resultSet))));
    }

    private User getManger(ResultSet resultSet) throws SQLException {
        ProgramManger user = new ProgramManger();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password"));
        user.setRegistration_date(resultSet.getDate("registration_date"));
        return user;
    }

    public int deleteAdminById(int id) {
        final String query = "DELETE FROM admin_user WHERE id = ?";

        return jdbcTemplate.update(query,id);
    }

    public int deleteMangerByID(int id) {
        final String query = "DELETE FROM program_manger WHERE id = ?";

        return jdbcTemplate.update(query,id);
    }


    public int updateUserByType(User user, String type){
        final String query = "UPDATE "+type+" SET user_name = ?, password = ? WHERE id = ?";

        return jdbcTemplate.update(query, preparedStatement ->
        {
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3 ,user.getId());
        });
    }

}