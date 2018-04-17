package com.DAO;


import com.Entity.Street;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class StreetDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public Collection<Street> getAllStreets(){
        final String query = "SELECT road,region, local_authority, road_category " +
                           "FROM major_road_counting_point GROUP BY road";
        return jdbcTemplate.query(query,(resultSet, i) -> getStreet(resultSet));

    }
    private Street getStreet(ResultSet resultSet) throws SQLException {
        Street street = new Street();
        street.setRoad(resultSet.getString("road"));
        street.setRegion(resultSet.getString("region"));
        street.setLocalAuthority(resultSet.getString("local_authority"));
        street.setRoadCategory(resultSet.getString("road_category"));
        return street;

    }


}
