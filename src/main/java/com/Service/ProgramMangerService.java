package com.Service;


import com.DAO.ProgramMangerDao;
import com.Entity.ProgramManger;
import com.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.text.ParseException;
import java.util.Collection;

@Service
public class ProgramMangerService implements UserService{

    @Autowired
    ProgramMangerDao programMangerDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    public Collection<User> getUsers() {
        try {
            return programMangerDao.getUsers();
        }catch (IncorrectResultSizeDataAccessException e){
            e.printStackTrace();
            return null;

        }
    }
    public User getUserById(int id) {

        try {
            return programMangerDao.getUserById(id);
        }catch(IncorrectResultSizeDataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    public int logIn(User user){
        User userInDb = programMangerDao.getUserByName(user.getName());
        if(userInDb == null) return -1;

        if (user.getName().equalsIgnoreCase(userInDb.getName()) &&
                passwordEncoder.matches(user.getPassword(),userInDb.getPassword()))
            try {
                return Integer.parseInt(userInDb.getId());
            }catch (NumberFormatException e){
                System.out.println(e.getLocalizedMessage());
            }
        return -1;
    }


}
