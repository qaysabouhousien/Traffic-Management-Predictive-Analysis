package com.DAO;

import com.Entity.CountingPoint;
import com.Entity.MajorRoadCountingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;


@Repository
public class MajorRoadCountingPointDao implements CountingPointDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Collection<CountingPoint> getCountingPoints() {

        final String query ="SELECT * from major_road_counting_point";
        return jdbcTemplate.query(query,((resultSet, i) -> getCountingPoint(resultSet)));
    }

    @Override
    public CountingPoint getCountingPointById(int id) {
        final String query = "SELECT * from major_road_counting_point where CP =? limit 1";
        try {
            return jdbcTemplate.queryForObject(query,((resultSet, i) -> getCountingPoint(resultSet)),id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public CountingPoint getCountingPoint(ResultSet resultSet) throws SQLException{

        MajorRoadCountingPoint cp = new MajorRoadCountingPoint();

        cp.setCp(resultSet.getInt("CP"));
        cp.setRegion(resultSet.getString("region"));
        cp.setLocalAuthority(resultSet.getString("local_Athority"));
        cp.setLocalAuthorityCode(resultSet.getString("local_athority_code"));
        cp.setsRefE(resultSet.getInt("S_ref_E"));
        cp.setsRefN(resultSet.getInt("s_ref_n"));
        cp.setsRefLatitude(resultSet.getDouble("s_ref_latitude"));
        cp.setsRefLongitude(resultSet.getDouble("s_ref_longitude"));
        cp.setRoad(resultSet.getString("road"));
        cp.setAJunction(resultSet.getString("a_junction"));
        cp.setBJunction(resultSet.getString("b_junction"));
        cp.setRoadCategory(resultSet.getString("road_category"));
        cp.setLinkLengthMiles(resultSet.getDouble("link_length_miles"));
        cp.setLinkLengthKm(resultSet.getDouble("link_length_km"));
        cp.setaRefE(resultSet.getDouble("a_ref_e"));
        cp.setARefN(resultSet.getDouble("a_ref_n"));
        cp.setBRefE(resultSet.getDouble("b_ref_e"));
        cp.setBRefN(resultSet.getDouble("b_ref_n"));
        return cp;
    }

    @Override
    public Collection<CountingPoint> getRoadCountingPoint(String roadName) {
        final String query = "SELECT * FROM major_road_counting_point where road = ? ";
        try {
            return jdbcTemplate.query(query,((resultSet, i) -> getCountingPoint(resultSet)),roadName);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
