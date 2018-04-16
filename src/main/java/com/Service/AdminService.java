package com.Service;


import com.DAO.AdminDao;
import com.Entity.Admin;
import com.Entity.ProgramManger;
import com.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Collection;

@Service
public class AdminService {

    @Autowired
    AdminDao adminDao;

    public Collection<User> getUsers() {
        try {
            return adminDao.getUsers();
        }catch(IncorrectResultSizeDataAccessException e){
            e.printStackTrace();
            return null;
        }    }

    public Collection<User> getAllUsers(){
        try {
            Collection<User> admins = adminDao.getUsers();
            Collection<User> mangers = adminDao.getMangers();
            admins.stream().forEach(admin -> admin.setType("Admin"));
            mangers.stream().forEach(manger -> manger.setType("Manger"));

            admins.addAll(mangers);
            return admins;
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
            return adminDao.addAdmin(user);
        if(user instanceof ProgramManger)
            return adminDao.addManger(user);
        return -1;
    }

    public int login(Admin user) {

        User userInDB = adminDao.getUserByName(user.getName());

        if (user.equals(userInDB))
            try {
                return Integer.parseInt(userInDB.getId());
            }catch (NumberFormatException e){
                System.out.println(e.getLocalizedMessage());
            }

        return -1;
    }
}
