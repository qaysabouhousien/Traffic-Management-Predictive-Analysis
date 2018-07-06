package com.Controller;

import com.Entity.TrafficCount;
import com.Service.TrafficCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;



// Not In Use In Client
@RestController
@RequestMapping("/TrafficCount")
public class TrafficCountController {

    @Autowired
    TrafficCountService trafficCountService;


    @RequestMapping(value = "/{CP}",method = RequestMethod.GET)
    public Collection<TrafficCount> getCPTrafficCount(@PathVariable("CP") int cp){
        return trafficCountService.getCPTrafficCount(cp);
    }


    @GetMapping(value = "/HighestTrafficCounts/{Limit}")
    public Collection<TrafficCount> getHighestTrafficCounts(@PathVariable("Limit") int limit){
        return trafficCountService.getHighestTrafficCounts(limit);
    }


}
