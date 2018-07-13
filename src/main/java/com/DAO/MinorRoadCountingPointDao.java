package com.DAO;

import com.Entity.CountingPoint;
import com.Entity.MajorRoadCountingPoint;
import com.Entity.MinorRoadCountingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Repo For pulling data from minor_road_counting_point table
 * @author - Qays
 */
@Repository
public class MinorRoadCountingPointDao implements CountingPointDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Gets All counting points
     * @return a collection of {@link CountingPoint}
     */
    @Override
    public Collection<CountingPoint> getCountingPoints() {
        final String query ="SELECT * from minor_road_counting_point";
        return jdbcTemplate.query(query,((resultSet, i) -> getCountingPoint(resultSet)));
    }

    /**
     * Gets Counting point by id
     * @param id counting point id
     * @return a {@link CountingPoint}
     */
    @Override
    public CountingPoint getCountingPointById(int id) {
        final String query = "SELECT * from minor_road_counting_point where CP =? limit 1";
        try {
            return jdbcTemplate.queryForObject(query,((resultSet, i) -> getCountingPoint(resultSet)),id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    /**
     *  Extract the data from the result set and constructs a {@link MinorRoadCountingPoint} object from it
     * @param resultSet  JDBC ResultSet
     * @return a {@link MinorRoadCountingPoint}
     * @throws SQLException resultSet Exception
     */
    @Override
    public CountingPoint getCountingPoint(ResultSet resultSet) throws SQLException {
        MinorRoadCountingPoint cp = new MinorRoadCountingPoint();

        cp.setCp(resultSet.getInt("CP"));
        cp.setRegion(resultSet.getString("region_name"));
        cp.setLocalAuthority(resultSet.getString("local_authority_name"));
        cp.setLocalAuthorityCode(resultSet.getString("local_authority_code"));
        cp.setsRefE(resultSet.getInt("S_ref_E"));
        cp.setsRefN(resultSet.getInt("s_ref_n"));
        cp.setsRefLatitude(resultSet.getDouble("s_ref_latitude"));
        cp.setsRefLongitude(resultSet.getDouble("s_ref_longitude"));
        cp.setRoad(resultSet.getString("road"));
        cp.setRoadName(resultSet.getString("road_name"));
        cp.setRoadCategory(resultSet.getString("road_category"));
        cp.setCpLocation(resultSet.getString("cp_location"));
        return cp;
    }

    /**
     * Gets specific Road Counting points
     * @param roadName Road Name
     * @return a collection of {@link MinorRoadCountingPoint}
     */
    @Override
    public Collection<CountingPoint> getRoadCountingPoint(String roadName) {
        final String query = "SELECT * FROM minor_road_counting_point where road = ? ";
        try {
            return jdbcTemplate.query(query,((resultSet, i) -> getCountingPoint(resultSet)),roadName);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
