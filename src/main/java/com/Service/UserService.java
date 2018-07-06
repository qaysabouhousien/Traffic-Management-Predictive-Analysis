package com.Service;


import com.Entity.User;


// Implemented In AdminService And ProgramManagerService
public interface UserService {
    public int logIn(User user);
    public User getUserById(int id);
}
