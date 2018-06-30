package com.Service;


import com.Configration.ApplicationConfigration;
import com.DAO.AddfCountDao;
import com.DAO.AdminDao;
import com.Entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        ApplicationConfigration.class,
        AddfCountServiceTestConfig.class}
)
public class AddfCountServiceTest {
    @Autowired
    AddfCountService service;

    @MockBean
    AddfCountDao dao;
    private Collection<AddfCount> addfCounts;

    @Before
    public void setUp(){

        int cp = 1;
        String region = "R";
        String localAuth = "LA";
        double latitude = 1;
        double longitude = 1;
        String roadName = "RN";
        String roadCategory = "RC";
        double linkLengthKm = 1;
        MajorRoadCountingPoint point =
                new MajorRoadCountingPoint(cp,region,localAuth,latitude,longitude,
                        roadName,roadCategory,linkLengthKm);

        int year = 2000;
        String estimationMethod = "EM";
        int allMv = 1;
        double trafficCapacityRatio= 1;
        int trafficStatus = 4;
        AddfStatus status = new AddfStatus(year,estimationMethod,allMv,trafficCapacityRatio,trafficStatus);
        Collection<AddfStatus> statuses = Arrays.asList(status);

        AddfCount count = new AddfCount(point,statuses);
        Collection<AddfCount> counts = Arrays.asList(count);
        this.addfCounts = counts;
        Mockito.when(dao.getAddfCount()).thenReturn(counts);


    }


    @Test
    public void testGetCounts(){
        assertThat(service.getAddfCount()).isEqualTo(this.addfCounts);
    }
}



@TestConfiguration
class AddfCountServiceTestConfig{
    @Bean
    public AddfCountService configAdminService(){
        return new AddfCountService();
    }
}