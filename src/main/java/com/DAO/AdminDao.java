package com.DAO;

import com.Entity.Admin;
import com.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class AdminDao implements UserDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Collection<User> getUsers() {

        final String query = "SELECT id,user_name ,password,registration_date " +
                "FROM admin_user ";


        return jdbcTemplate.query(query, (resultSet, i) -> getUser(resultSet));


    }
    @Override
    public User getUserById(int id) {

        final String query = "Select * FROM admin_user WHERE id = ? LIMIT 1";

        return jdbcTemplate.queryForObject(query,(resultSet, i) -> getUser(resultSet),id);
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

    @Override
    public int addUser(User user) {

        final String query = "INSERT INTO admin_user(user_name,password) VALUES(?,?)";

        return  jdbcTemplate.update(query,preparedStatement ->
                            {
                                preparedStatement.setString(1,user.getName());
                                preparedStatement.setString(2,user.getPassword());
                            }
                            );
    }
}
