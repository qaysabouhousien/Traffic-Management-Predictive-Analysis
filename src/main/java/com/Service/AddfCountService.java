package com.Service;


import com.DAO.AddfCountDao;
import com.Entity.AddfCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;


// Main Service In Application.
@Service
public class AddfCountService {


    @Autowired
    AddfCountDao addfCountDao;


    public Collection<AddfCount> getAddfCount(){
        return this.addfCountDao.getAddfCount();
    }

    public Collection<AddfCount> getAddfCountById(int cp) {
        return this.addfCountDao.getAddfCountById(cp);
    }


    public Collection<AddfCount> getCurrentRedCPs(int status) {
        Collection<AddfCount> a = this.addfCountDao.getCurrentRedCPs(status);
        System.out.println("Number Of CPs : "+a.size());
        return a;
    }

    public Collection<AddfCount> getPredictedStatuses() {
        Collection<AddfCount> addfCounts = this.addfCountDao.getPredictedStatuses();
        System.out.println("NumberOfPredicted Statuses CPs : "+ addfCounts.size());
        return addfCounts;
    }
}
