package com.Service;


import com.DAO.AddfCountDao;
import com.Entity.AddfCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;



@Service
/**
 * Main Service In Application.
 * @author - Qays
 */
public class AddfCountService {


    @Autowired
    AddfCountDao addfCountDao;

    /**
     * Gets All addf counts
     * @return a collection of {@link AddfCount}
     */
    public Collection<AddfCount> getAddfCount(){
        return this.addfCountDao.getAddfCount();
    }

    /**
     * Gets specified Counting point Addf Count
     * @param cp counting point id
     * @return a collection of {@link AddfCount}
     */
    public Collection<AddfCount> getAddfCountById(int cp) {
        return this.addfCountDao.getAddfCountById(cp);
    }

    /**
     *
     * @param status Point status Options : 1,2,3,4
     * @return a collection of {@link AddfCount}
     */
    public Collection<AddfCount> getCurrentStatusCPs(int status) {
        Collection<AddfCount> a = this.addfCountDao.getCurrentStatusCPs(status);
        System.out.println("Number Of CPs : "+a.size());
        return a;
    }

    /**
     * Gets all addf counts for the points which have a Predicated Status
     * @return a collection of {@link AddfCount}
     */
    public Collection<AddfCount> getPredictedStatuses() {
        Collection<AddfCount> addfCounts = this.addfCountDao.getPredictedStatuses();
        System.out.println("NumberOfPredicted Statuses CPs : "+ addfCounts.size());
        return addfCounts;
    }
}
