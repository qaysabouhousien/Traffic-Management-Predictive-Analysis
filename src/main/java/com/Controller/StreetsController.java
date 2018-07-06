package com.Controller;


import com.Entity.Street;
import com.Service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

// Removed From Client In Development.
@RestController
@RequestMapping("/Streets")
public class StreetsController {

    @Autowired
    StreetService streetService;

    @GetMapping()
    public Collection<Street> getAllStreets(){
        return this.streetService.getAllStreets();
    }
}
