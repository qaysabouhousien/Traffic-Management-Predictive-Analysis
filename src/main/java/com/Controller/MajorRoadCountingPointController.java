package com.Controller;


import com.Entity.CountingPoint;
import com.Entity.MajorRoadCountingPoint;
import com.Service.MajorRoadCountingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/MajorCountingPoint")
public class MajorRoadCountingPointController {


    @Autowired
    MajorRoadCountingPointService majorRoadCountingPointService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<MajorRoadCountingPoint> getCountingPoints() {
        return majorRoadCountingPointService.getCountingPoints();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public MajorRoadCountingPoint getCountingPointById(@PathVariable("id") int id) {
        return majorRoadCountingPointService.getCountingPointById(id);
    }

    @RequestMapping(value = "/RoadCountingPoints/{roadName}",method =RequestMethod.GET)
    public Collection<MajorRoadCountingPoint> getRoadCountingPoints(@PathVariable("roadName") String name){
        return majorRoadCountingPointService.getRoadCountingPoints(name);
    }





}
