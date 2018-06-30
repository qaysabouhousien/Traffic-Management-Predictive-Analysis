package com.DAO;


import com.Entity.AddfCount;
import com.Entity.AddfStatus;
import com.Entity.MajorRoadCountingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AddfCountDao {


    @Autowired
    JdbcTemplate jdbcTemplate;



    public Collection<AddfCount> getAddfCount(){
        final String query = "SELECT p.S_ref_longitude AS longitude,p.S_ref_latitude AS latitude," +
                            " addf.estimation_method AS estimation_method," +
                            " p.region AS region, p.local_authority AS local_authority," +
                            " p.road AS road, p.road_category AS road_category" +
                            ", p.link_length_km AS link_length_km,"+
                            "addf.cp AS cp,addf.year AS year,addf.all_mv AS all_mv" +
                            ",addf.traffic_capacity_ratio AS traffic_capacity_ratio," +
                            "addf.traffic_status AS traffic_status,"+
                            " addf.estimation_method AS estimation_method" +
                            " FROM updated_addf_data_major addf INNER JOIN " +
                            " major_road_counting_point p ON  addf.cp = p.cp"
                            + " WHERE addf.cp NOT IN " +
                                " (SELECT cp FROM updated_addf_data_major" +
                                " WHERE year =(SELECT MAX(year) FROM updated_addf_data_major)" +
                                " AND traffic_status =1)" +
                            " AND addf.cp IN " +
                                " (SELECT cp FROM updated_addf_data_major" +
                                " WHERE year =(SELECT MAX(year) FROM updated_addf_data_major))";
        return jdbcTemplate.query(query, new AddfMapExtractor());
    }


    public  Collection<AddfCount> getAddfCountById(int id){
        final String query = "SELECT p.S_ref_longitude AS longitude,p.S_ref_latitude AS latitude," +
                             " p.region AS region, p.local_authority AS local_authority," +
                             " p.road AS road, p.road_category AS road_category,"+
                             " p.link_length_km AS link_length_km, "+
                             " addf.estimation_method AS estimation_method," +
                             "addf.cp AS cp,addf.year AS year,addf.all_mv AS all_mv" +
                             ",addf.traffic_capacity_ratio AS traffic_capacity_ratio," +
                             "addf.traffic_status AS traffic_status"+
                             " FROM updated_addf_data_major addf INNER JOIN " +
                             " major_road_counting_point p ON  addf.cp = p.cp WHERE addf.cp = ?";
        return jdbcTemplate.query(query, new AddfMapExtractor(),id);
    }


    public Collection<AddfCount> getCurrentRedCPs(int status) {
        final String query = "SELECT p.S_ref_longitude AS longitude,p.S_ref_latitude AS latitude," +
                             " p.region AS region, p.local_authority AS local_authority," +
                             " p.road AS road, p.road_category AS road_category" +
                             ",p.link_length_km as link_length_km,"+
                             "addf.cp AS cp,addf.year AS year,addf.all_mv AS all_mv" +
                             ",addf.traffic_capacity_ratio AS traffic_capacity_ratio," +
                             "addf.traffic_status AS traffic_status," +
                             " addf.estimation_method AS estimation_method" +
                             " FROM updated_addf_data_major addf INNER JOIN " +
                             " major_road_counting_point p ON  addf.cp = p.cp"+
                             " WHERE addf.cp IN" +
                                " (SELECT cp FROM updated_addf_data_major" +
                                " WHERE year =(SELECT MAX(year) FROM updated_addf_data_major)" +
                                "AND traffic_status =?)";


        return jdbcTemplate.query(query, new AddfMapExtractor(),status);
    }

    public Collection<AddfCount> getPredictedStatuses() {
        final String query = "SELECT p.S_ref_longitude AS longitude,p.S_ref_latitude AS latitude," +
                " p.region AS region, p.local_authority AS local_authority," +
                " p.road AS road, p.road_category AS road_category" +
                ",p.link_length_km as link_length_km,"+
                "addf.cp AS cp,addf.year AS year,addf.all_mv AS all_mv" +
                ",addf.traffic_capacity_ratio AS traffic_capacity_ratio," +
                "addf.traffic_status AS traffic_status," +
                " addf.estimation_method AS estimation_method" +
                " FROM updated_addf_data_major addf INNER JOIN " +
                " major_road_counting_point p ON  addf.cp = p.cp" +
                " WHERE estimation_method = 'Model Prediction'";
        return jdbcTemplate.query(query,new AddfMapExtractor());

    }
}




class AddfMapExtractor
        implements ResultSetExtractor<Collection<AddfCount>> {


    /**
     * @ Author Qays Abou Housien
     * @param rs
     * @return Collection<AddfCount
     * @throws SQLException
     * @throws DataAccessException
      * *********************** *
      * Mapping the One To Many Relation Between The CP And Its Addf Counts Using A Java.util.Map
      * Map Key : MajorRoadCountingPoint, Java.util.Map Uses
      * The equals And hashCode Methods to Compare the Keys, Overriding The Methods and using Only
      * the CP attribute For Comparision.
      * *********************** *
     */
    @Override
    public Collection<AddfCount> extractData(ResultSet rs)
            throws SQLException, DataAccessException {

        Map<MajorRoadCountingPoint,Collection<AddfStatus>> cps = new HashMap<>();
        while (rs.next()){
            int cp = rs.getInt("cp");
            double longitude = rs.getDouble("longitude");
            double latitude  = rs.getDouble("latitude");
            String localAuthority = rs.getString("local_authority");
            String region = rs.getString("region");
            String roadName = rs.getString("road");
            String roadCategory = rs.getString("road_category");
            double linkLengthKM = rs.getDouble("link_length_km");
            int year = rs.getInt("year");
            String estimationMethod = rs.getString("estimation_method");
            int allMv = rs.getInt("all_mv");
            double ratio = rs.getDouble("traffic_capacity_ratio");
            int status = rs.getInt("traffic_status");
            MajorRoadCountingPoint newCp = new MajorRoadCountingPoint(cp,region,
                    localAuthority,latitude,longitude,roadName,roadCategory,linkLengthKM);
            Collection<AddfStatus> statuses = cps.get(newCp);
            AddfStatus addfStatus = new AddfStatus(year,estimationMethod,allMv,ratio,status);

//          If Null Means that the CP is not Yet Found So We insert a new Key and its New Statuses.
            if (statuses == null){
                Collection<AddfStatus> newStatuses = new LinkedList<>();
                newStatuses.add(addfStatus);
                cps.put(newCp,newStatuses);
            }else{
                statuses.add(addfStatus);
            }
        }

//        Converting The Map Into A Collection Of Addf Count.
        return cps.entrySet().stream()
                .map(entry -> new AddfCount(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());


    }
}



