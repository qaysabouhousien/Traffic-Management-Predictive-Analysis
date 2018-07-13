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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author - Qays
 */
@Service
public class AdminService implements UserService{

    @Autowired
    AdminDao adminDao;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    /**
     * Gets all admin users
     * @return a collection of admin Users
     */
    public Collection<User> getUsers() {
        try {
            return adminDao.getUsers();
        }catch(IncorrectResultSizeDataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets all users
     * @return a collection of {@link User}
     */
    public Collection<User> getAllUsers(){
        try {
            Collection<User> admins = adminDao.getUsers();
            Collection<User> mangers = adminDao.getMangers();
//            After Getting The Admins And Managers From the Dao,
//            We Assign For Each Admin And Manager Its Type
            admins.stream().forEach(admin -> {
                admin.setType("Admin");
                // Removing the password from each object so that it wont be sent to the client
                admin.setPassword("");
            });
            mangers.stream().forEach(manger -> {
                manger.setType("Manger");
                // Removing the password from each object so that it wont be sent to the client
                manger.setPassword("");
            });
//            Stream.concat() gets Two streams And Merge them into one
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

    /**
     * gets a user and calls the relevant DAO method based on the user's type.
     * password is encoded in this process
     * @param newUser User
     *  @return 1 on success/ -1 on failure
     */
    public int saveUser(User newUser){
//      Encoding The New Password
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

//        Checking If its Admin Or Manager In order to call the relevant Dao Method.
        if (newUser instanceof Admin)
            return adminDao.addAdmin(newUser);

        if(newUser instanceof ProgramManger)
            return adminDao.addManger(newUser);
        return -1;
    }

    /**
     * first operation is getting the user for the db by userName.
     * if its null then user doesn't exists and we return -1.
     * second operation is checking if the passwords are matching.
     * if true we return userId, else -1.
     * @param user User object, contains a userName and a password.
     * @return userId on success/ -1 on failure
     */
    @Override
    public int logIn(User user) {
//        Getting the User by name from the Dao Instance.
        User userInDB = adminDao.getUserByName(user.getName());

//        If user doesn't Exists It will be null, and we return -1 as Not Available.
        if(userInDB == null) return -1;

//      First We Check if the names Are Equal
//      and then we user the passwordEncoder to match the Passwords
        if (user.getName().equalsIgnoreCase(userInDB.getName())
                && passwordEncoder.matches(user.getPassword(), userInDB.getPassword())){
//            If this condition passes then userName And Password Are Correct.
            try {
                return Integer.parseInt(userInDB.getId());
            }catch (NumberFormatException e){
                System.out.println(e.getLocalizedMessage());
            }
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
