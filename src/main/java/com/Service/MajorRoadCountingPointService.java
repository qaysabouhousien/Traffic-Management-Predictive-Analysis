package com.Service;


import com.DAO.MajorRoadCountingPointDao;
import com.Entity.CountingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MajorRoadCountingPointService {

    @Autowired
    MajorRoadCountingPointDao majorRoadCountingPointDao;


    public Collection<CountingPoint> getCountingPoints() {
            return majorRoadCountingPointDao.getCountingPoints();
    }

    public CountingPoint getCountingPointById(int id) {
      return majorRoadCountingPointDao.getCountingPointById(id);
    }

}
