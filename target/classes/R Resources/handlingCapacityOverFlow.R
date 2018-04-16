
library(dplyr)

numoflanes=sendQuery("SELECT data.CP,data.iDir,data.year,data.d_count,data.hour,data.AMV,
                     point.link_length_km,point.local_Athority,point.number_of_lanes,point.region,
                     point.road,point.a_junction,point.b_junction,point.road_category,concat(point.S_ref_latitude,',',point.S_ref_longitude)
                     FROM major_roads_row_count_data data
                     inner join major_road_counting_point point on data.CP = point.CP 
                     order by data.year;")



numoflanes$capacityTrafficRatio =  numoflanes$AMV/(numoflanes$number_of_lanes*1900)
numoflanes = numoflanes %>% arrange(capacityTrafficRatio)  %>% filter(AMV > 1000) %>% filter(AMV < 2000)
cpApper <- numoflanes %>% group_by(CP) %>% count() %>% arrange(desc(n))


cp <- numoflanes %>% filter(CP == 99745)
