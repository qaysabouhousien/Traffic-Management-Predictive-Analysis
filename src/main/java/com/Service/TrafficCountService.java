package com.Service;


import com.DAO.TrafficCountDao;
import com.Entity.TrafficCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
/**
 * Service Not Yet Used.
 */
@Service
public class TrafficCountService {

    @Autowired
    TrafficCountDao trafficCountDao;


    public Collection<TrafficCount> getCPTrafficCount(int cp){
        return trafficCountDao.getCPTrafficCount(cp);
    }

    public Collection<TrafficCount> getHighestTrafficCounts(int limit){
        return trafficCountDao.getHighestTrafficCounts(limit);
    }
}
