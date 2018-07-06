query = paste("SELECT addf.*, countingPoint.link_length_km",
              " FROM updated_addf_data_major addf inner join major_road_counting_point ",
              " countingPoint on addf.cp = countingPoint.cp WHERE addf.year = 2016" ) 

addf2016 = sendQuery(query)

# Cumputing TTraffic From ADDF
computeTraffic =  function(x,col){
  if(as.numeric(x[1]) %% 4 == 0){
    res = as.numeric(x[col])*(as.numeric(x[18])*366) 
  }
  else{
    res = as.numeric(x[col])*(as.numeric(x[18])*365) 
  }
  return(res)
}


# PC 
UpdatedPC =  apply(addf2016, 1,function(x) computeTraffic(x,5))
addf2016[,"traffic_pc"] <- UpdatedPC

# 2WMV 
Updated2WMV = apply(addf2016, 1,function(x) computeTraffic(x,6))
addf2016[,"traffic_2WMV"] <- Updated2WMV

# Car
UpdatedCar = apply(addf2016, 1,function(x) computeTraffic(x,7))
addf2016[,"traffic_Car"] <- UpdatedCar

# Bus
UpdatedBus = apply(addf2016, 1,function(x) computeTraffic(x,8))
addf2016[,"traffic_Bus"] <- UpdatedBus

# LGV 
UpdatedLGV = apply(addf2016, 1,function(x) computeTraffic(x,9))
addf2016[,"traffic_lgv"] <- UpdatedLGV

# HGVR2
UpdatedHgvr2 = apply(addf2016, 1,function(x) computeTraffic(x,10))
addf2016[,"traffic_HGVR2"] <- UpdatedHgvr2

# HGVR3
UpdatedHgvr3 = apply(addf2016, 1,function(x) computeTraffic(x,11))
addf2016[,"traffic_HGVR3"] <- UpdatedHgvr3

# HGVR4
UpdatedHgvr4 = apply(addf2016, 1,function(x) computeTraffic(x,12))
addf2016[,"traffic_HGVR4"] <- UpdatedHgvr4

# HGVA3
UpdatedHgva3 = apply(addf2016, 1,function(x) computeTraffic(x,13))
addf2016[,"traffic_hgva3"] <- UpdatedHgva3

# HGVA5
UpdatedHgva5 = apply(addf2016, 1,function(x) computeTraffic(x,14))
addf2016[,"traffic_hgva5"] <- UpdatedHgva5

# HGVA6
UpdatedHgva6 = apply(addf2016, 1,function(x) computeTraffic(x,15))
addf2016[,"traffic_hgva6"] <- UpdatedHgva6


# HGV
UpdatedHgv = apply(addf2016, 1,function(x) computeTraffic(x,16))
addf2016[,"traffic_hgv"] <- UpdatedHgv


# ALLMV
UpdatedallMv = apply(addf2016, 1,function(x) computeTraffic(x,17))
addf2016[,"traffic_all_mv"] <- UpdatedallMv




# Getting Traffic
traffic2016 <- addf2016[,c(1:2,19:31)]


# Inserting to the db.
query <- "INSERT INTO updated_traffic(year,CP,PC,2WMV,car,bus,lgv,hgvr2,hgvr3,hgvr4,hgva3,hgva5,hgva6,hgv,allmv) VALUES("

oldPer = 0
for (i in 2:nrow(traffic2016)) {
  
  query2 = paste(query, traffic2016[i,1],",", traffic2016[i,2],",", traffic2016[i,3],",", 
                 traffic2016[i,4],",", traffic2016[i,5],",", traffic2016[i,6],","
                 , traffic2016[i,7],",", traffic2016[i,8],",", traffic2016[i,9],","
                 , traffic2016[i,10],",", traffic2016[i,11],",", traffic2016[i,12],","
                 , traffic2016[i,13],",", traffic2016[i,14],",", traffic2016[i,15],")")
  inserted = sendUpdate(query2)
  percent = round((i/nrow(traffic2016))*100,digits = 1)
  if(percent > oldPer){
    oldPer = percent
    print(paste("Percent : ",percent,"%",sep = ""))
    
  }
  
}
