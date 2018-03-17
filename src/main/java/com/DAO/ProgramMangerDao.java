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

@Repository
public class ProgramMangerDao implements UserDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Collection<User> getUsers() {
        final String query = "SELECT * FROM program_manger";
        return jdbcTemplate.query(query,((resultSet, i) -> (getUser(resultSet))));
    }

    @Override
    public User getUserById(int id) {
        final String query = "SELECT * FROM program_manger WHERE id =? LIMIT 1";

        try{
            return jdbcTemplate.queryForObject(query,(resultSet, i) -> getUser(resultSet),id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public User getUser(ResultSet resultSet) throws SQLException {
        ProgramManger user = new ProgramManger();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password"));
        user.setRegistration_date(resultSet.getDate("registration_date"));
        user.setRegistrationBy(resultSet.getInt("registration_by"));
        return user;
    }

    @Override
    public int addUser(User user) {

        final String query = "INSERT INTO program_manger(user_name,password,registration_by) VALUES(?,?,?)";

        ProgramManger programManger = (ProgramManger)user;



        return jdbcTemplate.update(query,preparedStatement ->
                                        {
                                            preparedStatement.setString(1,programManger.getName());
                                            preparedStatement.setString(2,programManger.getPassword());
                                            preparedStatement.setInt   (3,programManger.getRegistrationBy());
                                        });
    }

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
