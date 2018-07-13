package com.Controller;


import com.Entity.CountingPoint;
import com.Entity.MajorRoadCountingPoint;
import com.Service.MajorRoadCountingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
/**
 * Not in Use In Client
 * @author - Qays
 */
@RestController
@RequestMapping("/MajorCountingPoint")
public class MajorRoadCountingPointController {


    @Autowired
    MajorRoadCountingPointService majorRoadCountingPointService;

    /**
     * Gets all counting Points
     * @return a collection of {@link MajorRoadCountingPoint}
     */
    @RequestMapping(method = RequestMethod.GET)
    public Collection<MajorRoadCountingPoint> getCountingPoints() {
        return majorRoadCountingPointService.getCountingPoints();
    }

    /**
     * Gets counting point By id
     * @param id counting Point Id
     * @return a {@link MajorRoadCountingPoint}
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public MajorRoadCountingPoint getCountingPointById(@PathVariable("id") int id) {
        return majorRoadCountingPointService.getCountingPointById(id);
    }

    /**
     * Get Counting Points For A specific Road
     * @param name Road Name
     * @return a collection of {@link MajorRoadCountingPoint}
     */
    @RequestMapping(value = "/RoadCountingPoints/{roadName}",method =RequestMethod.GET)
    public Collection<MajorRoadCountingPoint> getRoadCountingPoints(@PathVariable("roadName") String name){
        return majorRoadCountingPointService.getRoadCountingPoints(name);
    }





}
