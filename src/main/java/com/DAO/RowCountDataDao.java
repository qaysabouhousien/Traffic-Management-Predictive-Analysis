package com.DAO;

import com.Entity.RowCountData;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface RowCountDataDao {

    Collection<RowCountData> getCountDataByCp(int cp);

    RowCountData getCountData(ResultSet resultSet) throws SQLException;

    Collection<RowCountData> getCountDataByCpAndYear(int cp, int year);
}
