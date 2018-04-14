package com.Service;


import com.DAO.AdminDao;
import com.Entity.Admin;
import com.Entity.ProgramManger;
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
            Collection<User> admins = adminDao.getUsers();
            Collection<User> mangers = adminDao.getMangers();
            for(User admin :admins){
                admin.setType("Admin");
            }
            for(User manger : mangers){
                manger.setType("Manger");
            }

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
            return 1;
        return -1;
    }
}
