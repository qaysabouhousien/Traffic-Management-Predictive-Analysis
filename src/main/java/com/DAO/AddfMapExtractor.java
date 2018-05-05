package com.DAO;

import com.Entity.AddfStatus;
import com.Entity.MajorRoadCountingPoint;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AddfMapExtractor
        implements ResultSetExtractor<Map<MajorRoadCountingPoint,Collection<AddfStatus>>> {
    @Override
    public Map<MajorRoadCountingPoint, Collection<AddfStatus>> extractData(ResultSet rs)
            throws SQLException, DataAccessException {

        Map<MajorRoadCountingPoint,Collection<AddfStatus>> cps = new HashMap<>();
        while (rs.next()){
            int cp = rs.getInt("cp");
            double longitude = rs.getDouble("longitude");
            double latitude  = rs.getDouble("latitude");
            int year = rs.getInt("year");
            int allMv = rs.getInt("all_mv");
            double ratio = rs.getDouble("traffic_capacity_ratio");
            int status = rs.getInt("traffic_status");
            AddfStatus addfStatus = new AddfStatus(year,allMv,ratio,status);
            MajorRoadCountingPoint newCp = new MajorRoadCountingPoint(cp,latitude,longitude);

            Collection<AddfStatus> statuses = cps.get(newCp);
            if (statuses == null){
                Collection<AddfStatus> newStatuses = new ArrayList<>();
                newStatuses.add(addfStatus);
                cps.put(newCp,newStatuses);
            }else{
                statuses.add(addfStatus);
            }


        }


        return cps;

    }
}
