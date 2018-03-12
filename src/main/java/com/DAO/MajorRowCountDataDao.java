package com.DAO;

import com.Entity.MajorRoadRowCountData;
import com.Entity.RowCountData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class MajorRowCountDataDao implements RowCountDataDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Collection<RowCountData> getCountDataByCp(int cp) {

        final String query = "SELECT * From major_roads_row_count_data where CP = ?";


        return jdbcTemplate.query(query,(resultSet, i) -> getCountData(resultSet),cp);
    }

    @Override
    public RowCountData getCountData(ResultSet resultSet) throws SQLException {

        RowCountData rowCountData = new MajorRoadRowCountData();

        rowCountData.setCp(resultSet.getInt("CP"));
        rowCountData.setiDir(resultSet.getString("iDir").charAt(0));
        rowCountData.setYear(resultSet.getInt("Year"));
        rowCountData.setdCount(resultSet.getDate("d_count"));
        rowCountData.setHour(resultSet.getInt("hour"));
        rowCountData.setPc(resultSet.getInt("PC"));
        rowCountData.setTwoWMV(resultSet.getInt("2WMV"));
        rowCountData.setCar(resultSet.getInt("car"));
        rowCountData.setBus(resultSet.getInt("bus"));
        rowCountData.setLvg(resultSet.getInt("lgv"));
        rowCountData.setHgvr2(resultSet.getInt("hgvr2"));
        rowCountData.setHgvr3(resultSet.getInt("hgvr3"));
        rowCountData.setHgvr4(resultSet.getInt("hgvr4"));
        rowCountData.setHgva5(resultSet.getInt("hgva5"));
        rowCountData.setHgva6(resultSet.getInt("hgva6"));
        rowCountData.setHgv(resultSet.getInt("hgv"));
        rowCountData.setAmv(resultSet.getInt("amv"));
        return  rowCountData;
    }

    @Override
    public Collection<RowCountData> getCountDataByCpAndYear(int cp, int year) {
        final String quey = "SELECT * FROM major_roads_row_count_data where CP = ? AND year = ? ";


        return jdbcTemplate.query(quey,(resultSet, i) -> getCountData(resultSet),cp,year);
    }
}
