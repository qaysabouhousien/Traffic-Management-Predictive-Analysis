package com.Service;


import com.Entity.User;




/**
 * @author - Qays
 */
public interface UserService {
    /**
     * Allows the user to connect to the application
     * @param user User object, contains a userName and a password.
     * @return userId if successful. -1 if not.
     */
    public int logIn(User user);

    /**
     *
     * @param id userId
     * @return User Object
     */
    public User getUserById(int id);
}
