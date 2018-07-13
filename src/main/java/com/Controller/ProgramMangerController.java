package com.Controller;


import com.Entity.ProgramManger;
import com.Entity.User;
import com.Service.ProgramMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Program Manager Don't Have Much Functionality, Login And Manager Data.
 * @author - Qays
 */
@RestController
@RequestMapping("/ProgramManager")
public class   ProgramMangerController {

    @Autowired
    ProgramMangerService programMangerService;

    /**
     * Gets all program Managers
     * @return list of all managers
     */
    @GetMapping()
    public Collection<User> getProgramMangers(){
        return programMangerService.getUsers();
    }


    /**
     * Get Program Manager by id
     * @param id manager Id
     * @return program Manager By the given Id
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") int id){
        return programMangerService.getUserById(id);
    }


    /**
     * login into the application,
     * @param user {@link ProgramManger}
     * @return return Id Of specified Admin if successful  Or -1 if failed.
     */
    @RequestMapping(value = "/Login" , method  = RequestMethod.POST)
    public int Login(@RequestBody ProgramManger user){
        return programMangerService.logIn(user);
    }




}
