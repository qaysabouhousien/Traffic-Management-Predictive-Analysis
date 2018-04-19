package com.Service;


import com.DAO.AdminDao;
import com.Entity.Admin;
import com.Entity.ProgramManger;
import com.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdminService {

    @Autowired
    AdminDao adminDao;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

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

    public int saveUser(User newUser){

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        if (newUser instanceof Admin)
            return adminDao.addAdmin(newUser);

        if(newUser instanceof ProgramManger)
            return adminDao.addManger(newUser);
        return -1;
    }

    public int login(Admin user) {

        User userInDB = adminDao.getUserByName(user.getName());
        if(userInDB == null) return -1;
        if (user.getName().equalsIgnoreCase(userInDB.getName())
                && passwordEncoder.matches(user.getPassword(), userInDB.getPassword()))
            try {
                return Integer.parseInt(userInDB.getId());
            }catch (NumberFormatException e){
                System.out.println(e.getLocalizedMessage());
            }
        return -1;
    }

    public int deleteAdminById(int id) {


        return adminDao.deleteAdminById(id);
    }

    public int deleteMangerById(int id) {

        return adminDao.deleteMangerByID(id);
    }

    public int updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user instanceof Admin ?
                adminDao.updateUserByType(user,"admin_user")
                : adminDao.updateUserByType(user,"program_manger");

    }
}
