package com.DAO;


import com.Entity.Street;
import com.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Streets dao working with major_road_counting_point table
 * @author - Qays
 */
@Repository
public class StreetDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Gets all streets
     * @return a collection of {@link Street}
     */
    public Collection<Street> getAllStreets(){
        final String query = "SELECT road,region, local_authority, road_category " +
                           "FROM major_road_counting_point GROUP BY road";
        return jdbcTemplate.query(query,(resultSet, i) -> getStreet(resultSet));

    }

    /**
     * Extract the result from the result set and Builds an street from it.
     * @param resultSet Sql ResultSet
     * @return extracted {@link Street}
     * @throws SQLException fetching results from resultSet throws an SQLException.
     */
    private Street getStreet(ResultSet resultSet) throws SQLException {
        Street street = new Street();
        street.setRoad(resultSet.getString("road"));
        street.setRegion(resultSet.getString("region"));
        street.setLocalAuthority(resultSet.getString("local_authority"));
        street.setRoadCategory(resultSet.getString("road_category"));
        return street;

    }


}
