package com.Service;


import com.DAO.AdminDao;
import com.Entity.Admin;
import com.Entity.ProgramManger;
import com.Entity.User;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AdminService implements UserService{

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
            admins.stream().forEach(admin -> {
                admin.setType("Admin");
                admin.setPassword("");
            });
            mangers.stream().forEach(manger -> {
                manger.setType("Manger");
                manger.setPassword("");
            });
            System.out.println(admins);
            System.out.println(mangers);
            Collection<User> users =
                    Stream.concat(admins.stream(),mangers.stream()).collect(Collectors.toList());
            return users;
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
    @Override
    public int logIn(User user) {

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
