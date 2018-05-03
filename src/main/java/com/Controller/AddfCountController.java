package com.Controller;


import com.Entity.AddfCount;
import com.Service.AddfCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/Status")
public class AddfCountController {

    @Autowired
    AddfCountService addfCountService;


    @GetMapping()
    public Collection<AddfCount> getStatus(){
        return this.addfCountService.getAddfCount();
    }

    @GetMapping("/{CP}")
    public  Collection<AddfCount> getStatusByID(@PathVariable("CP") int cp){
        return this.addfCountService.getAddfCountById(cp);
    }

    @GetMapping("/GetCPsByCurrentStatus/{Status}")
    public Collection<AddfCount> getCurrentRedCPs(@PathVariable("Status") int status){
        return this.addfCountService.getCurrentRedCPs(status);
    }



}
