package com.DAO;

import com.Entity.CountingPoint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface CountingPointDao {

    Collection<CountingPoint> getCountingPoints();

    CountingPoint getCountingPointById(int id);

    CountingPoint getCountingPoint(ResultSet resultSet) throws SQLException;

    Collection<CountingPoint> getRoadCountingPoint(String roadName);
}
