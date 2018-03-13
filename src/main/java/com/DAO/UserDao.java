package com.DAO;


import com.Entity.User;

import javax.jws.soap.SOAPBinding;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface UserDao{


    Collection<User> getUsers();

    User getUserById(int id);

    User getUser(ResultSet resultSet) throws SQLException;

    int addUser(User user);

    User getUserByName(String name);
}
