package com.Controller;


import com.Entity.CountingPoint;
import com.Service.MajorRoadCountingPointService;
import com.Service.MinorRoadCountingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/MinorCountingPoint")
public class MinorRoadCountingPointController {


    @Autowired
    MinorRoadCountingPointService minorRoadCountingPointService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<CountingPoint> getCountingPoints() {
        return minorRoadCountingPointService.getCountingPoints();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CountingPoint getCountingPointById(@PathVariable("id") int id) {
        return minorRoadCountingPointService.getCountingPointById(id);
    }

    @RequestMapping(value = "/RoadCountingPoints/{roadName}",method =RequestMethod.GET)
    public Collection<CountingPoint> getRoadCountingPoints(@PathVariable("roadName") String name){
        return minorRoadCountingPointService.getRoadCountingPoints(name);
    }



}
