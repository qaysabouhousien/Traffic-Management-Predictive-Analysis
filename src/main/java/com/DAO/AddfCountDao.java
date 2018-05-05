package com.DAO;


import com.Entity.AddfStatus;
import com.Entity.MajorRoadCountingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Map;
@Repository
public class AddfCountDao {


    @Autowired
    JdbcTemplate jdbcTemplate;



    public Map<MajorRoadCountingPoint,Collection<AddfStatus>> getAddfCount(){
        final String query = "SELECT p.S_ref_longitude AS longitude,p.S_ref_latitude AS latitude," +
                            "addf.cp AS cp,addf.year AS year,addf.all_mv AS all_mv" +
                            ",addf.traffic_capacity_ratio AS traffic_capacity_ratio," +
                            "addf.traffic_status AS traffic_status"+
                            " FROM updated_addf_data_major addf INNER JOIN " +
                            " major_road_counting_point p ON  addf.cp = p.cp" +
                            " WHERE addf.cp between 501 AND 504";
        return jdbcTemplate.query(query, new AddfMapExtractor());
    }


    public  Map<MajorRoadCountingPoint,Collection<AddfStatus>> getAddfCountById(int id){
        final String query = "SELECT p.S_ref_longitude AS longitude,p.S_ref_latitude AS latitude," +
                             "addf.cp AS cp,addf.year AS year,addf.all_mv AS all_mv" +
                             ",addf.traffic_capacity_ratio AS traffic_capacity_ratio," +
                             "addf.traffic_status AS traffic_status"+
                             " FROM updated_addf_data_major addf INNER JOIN " +
                             " major_road_counting_point p ON  addf.cp = p.cp WHERE addf.cp = ?";
        return jdbcTemplate.query(query, new AddfMapExtractor(),id);
    }


    public Map<MajorRoadCountingPoint,Collection<AddfStatus>> getCurrentRedCPs(int status) {
        final String query = "SELECT p.S_ref_longitude AS longitude,p.S_ref_latitude AS latitude," +
                             "addf.cp AS cp,addf.year AS year,addf.all_mv AS all_mv" +
                             ",addf.traffic_capacity_ratio AS traffic_capacity_ratio," +
                             "addf.traffic_status AS traffic_status" +
                             " FROM updated_addf_data_major addf INNER JOIN " +
                             " major_road_counting_point p ON  addf.cp = p.cp"+
                             " WHERE addf.cp IN" +
                                " (SELECT cp FROM updated_addf_data_major" +
                                " WHERE year =(SELECT MAX(year) FROM updated_addf_data_major)" +
                                "AND traffic_status =?)";


        return jdbcTemplate.query(query, new AddfMapExtractor(),status);
    }
}



