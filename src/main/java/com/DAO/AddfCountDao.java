package com.DAO;


import com.Entity.AddfStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Map;
@Repository
public class AddfCountDao {


    @Autowired
    JdbcTemplate jdbcTemplate;



    public Map<Integer,Collection<AddfStatus>> getAddfCount(){
        final String query = "SELECT cp,year,all_mv,traffic_capacity_ratio,traffic_status " +
                            " FROM updated_addf_data_major WHERE cp between 501 AND 504";
        Map<Integer,Collection<AddfStatus>> cps = jdbcTemplate.query(query, new AddfMapExtractor());


        return cps;
    }


    public  Map<Integer,Collection<AddfStatus>> getAddfCountById(int id){
        final String query = "SELECT cp,year,all_mv,traffic_capacity_ratio,traffic_status " +
                " FROM updated_addf_data_major WHERE cp = ?";
        return jdbcTemplate.query(query, new AddfMapExtractor(),id);
    }



}



