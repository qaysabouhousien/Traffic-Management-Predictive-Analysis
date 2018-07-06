package com.Service;


import com.DAO.StreetDao;
import com.Entity.Street;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
// Removed From Client In Development.
@Service
public class StreetService {

    @Autowired
    StreetDao streetDao;


    public Collection<Street> getAllStreets(){
        return this.streetDao.getAllStreets();
    }


}
