package com.DAO;

import com.Entity.MajorRoadCountingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author - Qays
 * Repo For reteving data from major_road_counting_point table
 */
@Repository
public class MajorRoadCountingPointDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Gets all counting points
     * @return a collection of {@link MajorRoadCountingPoint}
     */
    public Collection<MajorRoadCountingPoint> getCountingPoints() {

        final String query ="SELECT cp,S_ref_longitude As longitude,S_ref_latitude AS latitude " +
                            "FROM major_road_counting_point";
        return jdbcTemplate.query(query,((resultSet, i) -> getCountingPoint(resultSet)));
    }

    /**
     *
     * @param id counting point id
     * @return a {@link MajorRoadCountingPoint}
     */
    public MajorRoadCountingPoint getCountingPointById(int id) {
        final String query = "SELECT cp,S_ref_latitude AS latitude , S_ref_longitude AS longitude " +
                             "from major_road_counting_point where CP =? limit 1";
        try {
            return jdbcTemplate.queryForObject(query,((resultSet, i) -> getCountingPoint(resultSet)),id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    /**
     *  Extract the data from the result set and constructs a {@link MajorRoadCountingPoint} object from it
     * @param resultSet  JDBC ResultSet
     * @return a {@link MajorRoadCountingPoint}
     * @throws SQLException resultSet Exception
     */
    public MajorRoadCountingPoint getCountingPoint(ResultSet resultSet) throws SQLException{

        MajorRoadCountingPoint cp = new MajorRoadCountingPoint();

        cp.setCp(resultSet.getInt("CP"));
        cp.setLatitude(resultSet.getDouble("latitude"));
        cp.setLongitude(resultSet.getDouble("longitude"));
        return cp;
    }

    /**
     * Gets specific Road Counting points
     * @param roadName Road Name
     * @return a collection of {@link MajorRoadCountingPoint}
     */
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
