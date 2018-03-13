package com.Service;


import com.DAO.ProgramMangerDao;
import com.Entity.ProgramManger;
import com.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.Collection;

@Service
public class ProgramMangerService {

    @Autowired
    ProgramMangerDao programMangerDao;


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

    public int saveUser(User user){
        if (user instanceof ProgramManger)
            return programMangerDao.addUser(user);
        return -1;
    }


    public int logIn(User user){
        User userInDb = programMangerDao.getUserByName(user.getName());
        if (user.equals(userInDb))
            return 1;
        return -1;
    }


}
