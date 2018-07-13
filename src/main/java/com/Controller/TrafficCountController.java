package com.Controller;

import com.Entity.TrafficCount;
import com.Service.TrafficCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;



/**
 * Not In Use In Client
 * @author - Qays
 */
@RestController
@RequestMapping("/TrafficCount")
public class TrafficCountController {

    @Autowired
    TrafficCountService trafficCountService;

    /**
     *  Gets specific Cp Traffic Counts
     * @param cp counting point Id
     * @return a collection of  {@link TrafficCount}
     */
    @RequestMapping(value = "/{CP}",method = RequestMethod.GET)
    public Collection<TrafficCount> getCPTrafficCount(@PathVariable("CP") int cp){
        return trafficCountService.getCPTrafficCount(cp);
    }

    /**
     * returns highest traffic counts
     * @param limit limit of results
     * @return list of traffic counts
     */
    @GetMapping(value = "/HighestTrafficCounts/{Limit}")
    public Collection<TrafficCount> getHighestTrafficCounts(@PathVariable("Limit") int limit){
        return trafficCountService.getHighestTrafficCounts(limit);
    }


}
