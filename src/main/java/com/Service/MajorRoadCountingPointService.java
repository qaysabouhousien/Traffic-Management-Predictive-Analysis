package com.Service;


import com.DAO.MajorRoadCountingPointDao;
import com.Entity.CountingPoint;
import com.Entity.MajorRoadCountingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


/**
 * Service Not Yet Used.
 */
@Service
public class MajorRoadCountingPointService {

    @Autowired
    MajorRoadCountingPointDao majorRoadCountingPointDao;


    public Collection<MajorRoadCountingPoint> getCountingPoints() {
            return majorRoadCountingPointDao.getCountingPoints();
    }

    public MajorRoadCountingPoint getCountingPointById(int id) {
      return majorRoadCountingPointDao.getCountingPointById(id);
    }


    public Collection<MajorRoadCountingPoint> getRoadCountingPoints(String roadName){
        return majorRoadCountingPointDao.getRoadCountingPoint(roadName);
    }

}
