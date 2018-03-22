package com.DAO;

import com.Entity.TrafficCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class TrafficCountDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public Collection<TrafficCount> getCPTrafficCount(int cp){
        final String query = "SELECT * FROM traffic where cp = ?";
        try {
            return jdbcTemplate.query(query, ((resultSet, i) -> getTrafficCount(resultSet)), cp);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    private TrafficCount getTrafficCount(ResultSet resultSet) throws SQLException {
        TrafficCount c = new TrafficCount();

        c.setCp(resultSet.getInt("CP"));
        c.setYear(resultSet.getInt("year"));
        c.setPc(resultSet.getDouble("PC"));
        c.setTwoWMV(resultSet.getDouble("2WMV"));
        c.setCar(resultSet.getDouble("car"));
        c.setBus(resultSet.getDouble("bus"));
        c.setLgv(resultSet.getDouble("lgv"));
        c.setHgvr2(resultSet.getDouble("hgvr2"));
        c.setHgvr3(resultSet.getDouble("hgvr3"));
        c.setHgvr4(resultSet.getDouble("hgvr4"));
        c.setHgva3(resultSet.getDouble("hgva3"));
        c.setHgva5(resultSet.getDouble("hgva5"));
        c.setHgva6(resultSet.getDouble("hgva6"));
        c.setHgv(resultSet.getDouble("hgv"));
        c.setAllMV(resultSet.getDouble("allmv"));
        return c;
    }


}
