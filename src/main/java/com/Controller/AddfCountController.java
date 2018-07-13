package com.Controller;


import com.Entity.AddfCount;
import com.Service.AddfCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;



/**
 * Main Application Controller,
 * Gets All CPs Data
 * @author - Qays
 */
@RestController
@RequestMapping("/Status")
public class AddfCountController {

    @Autowired
    AddfCountService addfCountService;

    /**
     *  Gets all Predicted Statuses
     * @return addf counts list
     */
    @GetMapping("/PredictedStatuses")
    public Collection<AddfCount> getPredictedStatuses(){
        return this.addfCountService.getPredictedStatuses();
    }
    /**
     *  Gets all statuses.
     *  @return addf counts list
     */
    @GetMapping()
    public Collection<AddfCount> getStatus(){
        return this.addfCountService.getAddfCount();
    }

    /**
     * Gets counting points statuses for specified counting point
     * @param cp counting point's id
     * @return addf counts list
     */
    @GetMapping("/{CP}")
    public  Collection<AddfCount> getStatusByID(@PathVariable("CP") int cp){
        return this.addfCountService.getAddfCountById(cp);
    }

    /**
     *  Gets counting points statuses for specified status
     * @param status point status - could be 1,2,3 or 4
     * @return addf counts list
     */
    @GetMapping("/GetCPsByCurrentStatus/{Status}")
    public Collection<AddfCount> getCurrentRedCPs(@PathVariable("Status") int status){
        return this.addfCountService.getCurrentStatusCPs(status);
    }



}
