package com.Controller;


import com.Entity.RowCountData;
import com.Service.MajorRoadRowCountDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
/**
 * Not In Use In Client
 * @author - Qays
 */
@RestController
@RequestMapping("/MajorRowCountData")
public class MajorRoadRowCountDataController {

    @Autowired
    MajorRoadRowCountDataService service;

    /**
     * Get Counting Point row data in a specific year
     * @param cp Counting Point Id
     * @param year count Year
     * @return a collection of {@link RowCountData}
     */
    @RequestMapping(value = "{CP}/{year}",method = RequestMethod.GET)
    public Collection<RowCountData> getCountDataByCpAndYear(@PathVariable("CP") int cp ,@PathVariable("year") int year) {
        return service.getCountDataByCpAndYear(cp,year);
    }

    /**
     * Gets counting point row Data in all the years
     * @param cp counting point id
     * @return a collection of {@link RowCountData}
     */
    @RequestMapping(value = "{CP}",method = RequestMethod.GET)
    public Collection<RowCountData> getCountDataByCp(@PathVariable("CP") int cp) {
        return service.getCountDataByCp(cp);
    }


}
