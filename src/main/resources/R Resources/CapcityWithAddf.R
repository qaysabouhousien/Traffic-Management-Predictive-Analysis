

library(dplyr)
addf = sendQuery("SELECT data.CP,data.estimation_method,data.year,data.fd_all_mv,
                     point.link_length_km,point.local_Authority,point.number_of_lanes,point.region,
                     point.road,point.a_junction,point.b_junction,point.road_category,concat(point.S_ref_latitude,',',point.S_ref_longitude)
                     FROM addf_data_major data
                     inner join major_road_counting_point point on data.CP = point.CP 
                     order by data.year;")


addf$capacityTrafficRatio =  addf$fd_all_mv/(addf$number_of_lanes*1900*24)


addf %>% filter(capacityTrafficRatio > 1) %>% distinct(CP) %>%  count()






addf$capacityGroups <- cut(addf$capacityTrafficRatio, c(seq(0,1,0.1),5))
addf <- addf %>% arrange(desc(capacityTrafficRatio))



roadCatCapOverFlow <- addf %>% filter(capacityGroups == "(1,5]") %>% filter(road_category == "PU")


cp = addf %>% filter(CP == 501)

cp = cp[,c("year","capacityTrafficRatio")]

# plot(cp,)
capacityRatio =  addf %>% group_by(capacityGroups) %>% count()



capacityRatio$percent = (capacityRatio$n /nrow(addf))*100

plot(capacityRatio[,1:2],main = "Traffic Capacity Ratio Distribution",xlab = "Traffic Capacity Ratio", ylab = "Number Of Obs.")


max(addf$capacityTrafficRatio)
