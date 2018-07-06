package com.Controller;


import com.Entity.RowCountData;
import com.Service.MajorRoadRowCountDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

// Not In Use In Client
@RestController
@RequestMapping("/MajorRowCountData")
public class MajorRoadRowCountDataController {

    @Autowired
    MajorRoadRowCountDataService service;


    @RequestMapping(value = "{CP}/{year}",method = RequestMethod.GET)
    public Collection<RowCountData> getCountDataByCpAndYear(@PathVariable("CP") int cp ,@PathVariable("year") int year) {
        return service.getCountDataByCpAndYear(cp,year);
    }
    @RequestMapping(value = "{CP}",method = RequestMethod.GET)
    public Collection<RowCountData> getCountDataByCp(@PathVariable("CP") int cp) {
        return service.getCountDataByCp(cp);
    }


}
