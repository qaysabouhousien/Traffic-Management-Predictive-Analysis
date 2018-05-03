package com.Service;


import com.DAO.AddfCountDao;
import com.Entity.AddfCount;
import com.Entity.AddfStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AddfCountService {


    @Autowired
    AddfCountDao addfCountDao;


    public Collection<AddfCount> getAddfCount(){
        return convertMapToCollection(this.addfCountDao.getAddfCount());
    }

    public Collection<AddfCount> getAddfCountById(int cp) {
        return convertMapToCollection(this.addfCountDao.getAddfCountById(cp));
    }

    private Collection<AddfCount> convertMapToCollection(Map<Integer,Collection<AddfStatus>> cps) {
        //        Converting the map into a ADDFCount collection.
        return cps.entrySet().stream()
                .map(entry -> new AddfCount(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public Collection<AddfCount> getCurrentRedCPs(int status) {
        Collection<AddfCount> a = convertMapToCollection(this.addfCountDao.getCurrentRedCPs(status));
        System.out.println("Number Of CPs : "+a.size());
        return a;
    }
}
