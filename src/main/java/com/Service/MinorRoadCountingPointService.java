package com.Service;


import com.DAO.MajorRoadCountingPointDao;
import com.DAO.MinorRoadCountingPointDao;
import com.Entity.CountingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
// Controller Not Yet Used.
@Service
public class MinorRoadCountingPointService {

    @Autowired
    MinorRoadCountingPointDao minorRoadCountingPointDao;


    public Collection<CountingPoint> getCountingPoints() {
        return minorRoadCountingPointDao.getCountingPoints();
    }

    public CountingPoint getCountingPointById(int id) {
        return minorRoadCountingPointDao.getCountingPointById(id);
    }


    public Collection<CountingPoint> getRoadCountingPoints(String roadName){
        return minorRoadCountingPointDao.getRoadCountingPoint(roadName);
    }
}
