package com.Service;


import com.Entity.User;

public interface UserService {
    public int logIn(User user);
    public User getUserById(int id);
}
