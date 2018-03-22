package com.Controller;

import com.Entity.TrafficCount;
import com.Service.TrafficCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/TrafficCount")
public class TrafficCountController {

    @Autowired
    TrafficCountService trafficCountService;


    @RequestMapping(value = "/{CP}",method = RequestMethod.GET)
    public Collection<TrafficCount> getCPTrafficCount(@PathVariable("CP") int cp){
        return trafficCountService.getCPTrafficCount(cp);
    }


}
