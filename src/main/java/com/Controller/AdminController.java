package com.Controller;


import com.Entity.Admin;
import com.Entity.ProgramManger;
import com.Entity.User;
import com.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Admin Have All Authority on Users, He can get All Users,
 * Remove User, Change User And Add New User.
 * @author - Qays
 */
@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * gets All admins
     * @return list of all admins
     */
    @GetMapping()
    public Collection<User> getAdmins(){return  adminService.getUsers();}

    /**
     * gets All users
     * @return list of all users
     */
    @GetMapping(value = "/GetAllUsers")
    public Collection<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    /**
     * @param id user Id
     * @return user with specified id
     */
    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return adminService.getUserById(id);
    }

    /**
     * @param user {@link Admin}
     * @return new admin id/-1 if failed
     */
    @PostMapping(value = "/SaveAdmin")
    public int saveUser(@RequestBody Admin user){
        return adminService.saveUser(user);
    }
    /**
     * @param user {@link ProgramManger}
     * @return new manager id/-1 if failed
     */
    @PostMapping(value = "/SaveManager")
    public int saveUser(@RequestBody ProgramManger user){
        return adminService.saveUser(user);
    }

    /**
     * login into the application,
     * @param user {@link Admin}
     * @return return Id Of specified Admin if successful  Or -1 if failed.
     */
    @PostMapping(value = "/Login"   )
    public int logIn(@RequestBody Admin user){
        return adminService.logIn(user);
    }

    /**
     * deletes the admin
     * @param id admin Id
     * @return 1 on success, -1 on failure
     */
    @DeleteMapping(value = "/DeleteAdmin/{id}")
    public int deleteAdmin(@PathVariable("id") int id){
        return adminService.deleteAdminById(id);
    }

    /**
     * deletes the manager
     * @param id manager Id
     * @return 1 on success, -1 on failure
     */
    @DeleteMapping(value = "/DeleteManager/{id}")
    public int deleteManger(@PathVariable("id") int id ){
        return adminService.deleteMangerById(id);
    }

    /**
     * updated the admin's details.
     * @param admin {@link Admin}
     * @return 1 on success, -1 on failure
     */
    @PutMapping(value = "/UpdateAdmin")
    public int updateAdmin(@RequestBody Admin admin){
        return adminService.updateUser(admin);
    }

    /**
     * updates the program Manager's details
     * @param programManger {@link Admin}
     * @return 1 on success, -1 on failure
     */
    @PutMapping(value = "/UpdateManager")
    public int updateManger(@RequestBody ProgramManger programManger){
        return adminService.updateUser(programManger);
    }

}
