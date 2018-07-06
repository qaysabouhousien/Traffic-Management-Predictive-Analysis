package com.Service;


import com.DAO.MajorRowCountDataDao;
import com.Entity.RowCountData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

// Controller Not Yet Used.
@Service
public class MajorRoadRowCountDataService {

    @Autowired
    MajorRowCountDataDao majorRowCountDataDao;


    public Collection<RowCountData> getCountDataByCp(int cp) {

        return majorRowCountDataDao.getCountDataByCp(cp);
    }


    public Collection<RowCountData> getCountDataByCpAndYear(int cp,int year) {

        return majorRowCountDataDao.getCountDataByCpAndYear(cp,year);

    }
}
