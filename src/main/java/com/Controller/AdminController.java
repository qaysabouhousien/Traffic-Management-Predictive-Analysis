package com.Controller;


import com.Entity.Admin;
import com.Entity.ProgramManger;
import com.Entity.User;
import com.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getAllUsers(){
        return adminService.getUsers();
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") int id){
        return adminService.getUserById(id);
    }

    @RequestMapping(value = "/Save",method = RequestMethod.POST)
    public int saveUser(@RequestBody Admin user){
        return adminService.saveUser(user);
    }


    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public int logIn(@RequestBody Admin user){
        return adminService.login(user);
    }


}
