package com.DAO;

import com.Entity.MajorRoadCountingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;


@Repository
public class MajorRoadCountingPointDao{

    @Autowired
    JdbcTemplate jdbcTemplate;


    public Collection<MajorRoadCountingPoint> getCountingPoints() {

        final String query ="SELECT cp,S_ref_longitude As longitude,S_ref_latitude AS latitude " +
                            "FROM major_road_counting_point";
        return jdbcTemplate.query(query,((resultSet, i) -> getCountingPoint(resultSet)));
    }


    public MajorRoadCountingPoint getCountingPointById(int id) {
        final String query = "SELECT cp,S_ref_latitude AS latitude , S_ref_longitude AS longitude " +
                             "from major_road_counting_point where CP =? limit 1";
        try {
            return jdbcTemplate.queryForObject(query,((resultSet, i) -> getCountingPoint(resultSet)),id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }


    public MajorRoadCountingPoint getCountingPoint(ResultSet resultSet) throws SQLException{

        MajorRoadCountingPoint cp = new MajorRoadCountingPoint();

        cp.setCp(resultSet.getInt("CP"));
        cp.setLatitude(resultSet.getDouble("latitude"));
        cp.setLongitude(resultSet.getDouble("longitude"));
        return cp;
    }

    public Collection<MajorRoadCountingPoint> getRoadCountingPoint(String roadName) {
        final String query = "SELECT CP,S_ref_latitude AS latitude,S_ref_longitude AS longitude " +
                             "FROM major_road_counting_point where road = ? ";
        try {
            return jdbcTemplate.query(query,((resultSet, i) -> getCountingPoint(resultSet)),roadName);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
