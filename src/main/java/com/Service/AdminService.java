package com.Service;


import com.DAO.AdminDao;
import com.Entity.Admin;
import com.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdminService {

    @Autowired
    AdminDao adminDao;

    public Collection<User> getUsers() {
        try {
            return adminDao.getUsers();
        }catch (IncorrectResultSizeDataAccessException e){
            e.printStackTrace();
            return null;

        }
    }
    public User getUserById(int id) {

        try {
            return adminDao.getUserById(id);
        }catch(IncorrectResultSizeDataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    public int saveUser(User user){
        if (user instanceof Admin)
            return adminDao.addUser(user);
        return -1;
    }

    public int login(Admin user) {

        User userInDB = adminDao.getUserByName(user.getName());

        if (user.equals(userInDB))
            return 1;
        return -1;
    }
}
