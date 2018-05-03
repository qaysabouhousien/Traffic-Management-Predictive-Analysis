package com.DAO;

import com.Entity.AddfStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AddfMapExtractor
        implements ResultSetExtractor<Map<Integer,Collection<AddfStatus>>> {
    @Override
    public Map<Integer, Collection<AddfStatus>> extractData(ResultSet rs)
            throws SQLException, DataAccessException {
        Map<Integer,Collection<AddfStatus>> cps = new HashMap<>();
        while (rs.next()){
            int cp = rs.getInt("cp");
            int year = rs.getInt("year");
            int allMv = rs.getInt("all_mv");
            double ratio = rs.getDouble("traffic_capacity_ratio");
            int status = rs.getInt("traffic_status");
            AddfStatus adffstatus = new AddfStatus(year,allMv,ratio,status);
            Collection<AddfStatus> statuses = cps.get(cp);
            if (statuses == null){
                Collection<AddfStatus> newStatuses = new ArrayList<>();
                newStatuses.add(adffstatus);
                cps.put(cp,newStatuses);
            }else{
                statuses.add(adffstatus);
            }


        }
        return cps;

    }
}
