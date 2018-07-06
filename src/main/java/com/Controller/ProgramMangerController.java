package com.Controller;


import com.Entity.ProgramManger;
import com.Entity.User;
import com.Service.ProgramMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

// Program Manager Don't Have Much Functionality, Login And Manager Data.
@RestController
@RequestMapping("/ProgramManager")
public class   ProgramMangerController {

    @Autowired
    ProgramMangerService programMangerService;


    @GetMapping()
    public Collection<User> getProgramMangers(){
        return programMangerService.getUsers();
    }



    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") int id){
        return programMangerService.getUserById(id);
    }



    @RequestMapping(value = "/Login" , method  = RequestMethod.POST)
    public int Login(@RequestBody ProgramManger user){
        return programMangerService.logIn(user);
    }




}
