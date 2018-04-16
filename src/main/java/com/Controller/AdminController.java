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

    @GetMapping()
    public Collection<User> getAdmins(){return  adminService.getUsers();}


    @GetMapping(value = "/GetAllUsers")
    public Collection<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return adminService.getUserById(id);
    }

    @PostMapping(value = "/SaveAdmin")
    public int saveUser(@RequestBody Admin user){
        return adminService.saveUser(user);
    }

    @PostMapping(value = "/SaveManger")
    public int saveUser(@RequestBody ProgramManger user){
        return adminService.saveUser(user);
    }

    @PostMapping(value = "/Login"   )
    public int logIn(@RequestBody Admin user){
        return adminService.login(user);
    }


}
