package com.Controller;


import com.Entity.CountingPoint;
import com.Service.MajorRoadCountingPointService;
import com.Service.MinorRoadCountingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
/**
 * Not In Use In Client
 * @author - Qays
 */
@RestController
@RequestMapping("/MinorCountingPoint")
public class MinorRoadCountingPointController {


    @Autowired
    MinorRoadCountingPointService minorRoadCountingPointService;

    /**
     * Gets all minor counting points
     * @return a collection of {@link CountingPoint}
     */
    @RequestMapping(method = RequestMethod.GET)
    public Collection<CountingPoint> getCountingPoints() {
        return minorRoadCountingPointService.getCountingPoints();
    }

    /**
     * Get counting point by Id
     * @param id counting point id
     * @return a {@link CountingPoint}
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CountingPoint getCountingPointById(@PathVariable("id") int id) {
        return minorRoadCountingPointService.getCountingPointById(id);
    }

    /**
     * Gets a specific road Counting points
     * @param name Road Name
     * @return a collection of {@link CountingPoint}
     */
    @RequestMapping(value = "/RoadCountingPoints/{roadName}",method =RequestMethod.GET)
    public Collection<CountingPoint> getRoadCountingPoints(@PathVariable("roadName") String name){
        return minorRoadCountingPointService.getRoadCountingPoints(name);
    }



}
