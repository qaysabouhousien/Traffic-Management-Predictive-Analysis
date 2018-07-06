library(dplyr)

query = paste("SELECT addf.* , c.link_length_km , traffic.*  "
              ," FROM major_road_counting_point c  "
              ," inner join addf_data_major addf  on addf.cp = c.cp "
              ," left join updated_traffic traffic on c.cp = traffic.cp "
              ," AND addf.year = traffic.year"
              )

addf = sendQuery(query)

# Change the traffic Col Names. 
colnames(addf) <- c(colnames(addf[1:18]),"trafficYear", "TrafficCP",colnames(addf[21:length(addf)]))


# Change back the traffic units 
addf[21:length(addf)] <- addf[21:length(addf)]/1000 


# ADDF WITH TRAFFIC CONNECTION. 
addfWithTraffic = addf %>% filter(!is.na(PC) )

# Set missing values As Null: 
addfWithTraffic[addfWithTraffic == 0] <- NA 


computeAddf =  function(x,col){
      if(as.numeric(x[1]) %% 4 == 0){
        res = as.numeric(x[col])/(as.numeric(x[18])*366) 
      }
      else{
        res = as.numeric(x[col])/(as.numeric(x[18])*365) 
      }
      res = res * 1000
      return(res)
}

# PC 
UpdatedPC =  apply(addfWithTraffic, 1,function(x) computeAddf(x,21))
addfWithTraffic[,"fd_pc"] <- round(UpdatedPC,2)

# 2WMV 
Updated2WMV = apply(addfWithTraffic, 1,function(x) computeAddf(x,22))
addfWithTraffic[,"fd_2WMV"] <- round(Updated2WMV,2)

# Car
UpdatedCar = apply(addfWithTraffic, 1,function(x) computeAddf(x,23))
addfWithTraffic[,"fd_Car"] <- round(UpdatedCar,2)

# Bus
UpdatedBus = apply(addfWithTraffic, 1,function(x) computeAddf(x,24))
addfWithTraffic[,"fd_Bus"] <- round(UpdatedBus,2)

# LGV 
UpdatedLGV = apply(addfWithTraffic, 1,function(x) computeAddf(x,25))
addfWithTraffic[,"fd_lgv"] <- round(UpdatedLGV,2)

# HGVR2
UpdatedHgvr2 = apply(addfWithTraffic, 1,function(x) computeAddf(x,26))
addfWithTraffic[,"fd_HGVR2"] <- round(UpdatedHgvr2,2)

# HGVR3
UpdatedHgvr3 = apply(addfWithTraffic, 1,function(x) computeAddf(x,27))
addfWithTraffic[,"fd_HGVR3"] <- round(UpdatedHgvr3,2)

# HGVR4
UpdatedHgvr4 = apply(addfWithTraffic, 1,function(x) computeAddf(x,28))
addfWithTraffic[,"fd_HGVR4"] <- round(UpdatedHgvr4,2)

# HGVA3
UpdatedHgva3 = apply(addfWithTraffic, 1,function(x) computeAddf(x,29))
addfWithTraffic[,"fd_hgva3"] <- round(UpdatedHgva3,2)

# HGVA5
UpdatedHgva5 = apply(addfWithTraffic, 1,function(x) computeAddf(x,30))
addfWithTraffic[,"fd_hgva5"] <- round(UpdatedHgva5,2)

# HGVA6
UpdatedHgva6 = apply(addfWithTraffic, 1,function(x) computeAddf(x,31))
addfWithTraffic[,"fd_hgva6"] <- round(UpdatedHgva6,2)


# HGV
UpdatedHgv = apply(addfWithTraffic, 1,function(x) computeAddf(x,32))
addfWithTraffic[,"fd_hgv"] <- round(UpdatedHgv,2)


# ALLMV
UpdatedallMv = apply(addfWithTraffic, 1,function(x) computeAddf(x,33))
addfWithTraffic[,"fd_all_mv"] <- round(UpdatedallMv,2)


# Saving Backup 
write.csv(addfWithTraffic[,1:17],"addfWithTraffic.csv",row.names = FALSE)

addfWithTraffic = addfWithTraffic[,1:17]




# 
# ADDF WITHOUT TRAFFIC CONNECTION.
addfWithoutTraffic = addf %>% filter(is.na(PC))

addfWithoutTraffic = addfWithoutTraffic[,1:17]

# Saving Backup
write.csv(addfWithoutTraffic,"addfWithoutTraffic.csv", row.names = FALSE)

# # There are null values in addf without traffic. 
# # Handeling them with the same approch as traffic.
addfWithoutTraffic[addfWithoutTraffic == 0] <- NA 

# Fill in NA values with the values of the nearest neighbours 
# FAST THIS TIME AROUND
UpdatedaddfWithoutTraffic  = knnImputation(data = addfWithoutTraffic[,5:length(addfWithoutTraffic)]
                                    ,distData = addfWithTraffic[,5:length(addfWithoutTraffic)])

# Rounding to beautiful Numbers
UpdatedaddfWithoutTraffic <- round(UpdatedaddfWithoutTraffic,2)


# Returning Back the omited columns.

UpdatedaddfWithoutTraffic$year = addfWithoutTraffic$year
UpdatedaddfWithoutTraffic$CP= addfWithoutTraffic$CP
UpdatedaddfWithoutTraffic$estimation_method = addfWithoutTraffic$estimation_method
UpdatedaddfWithoutTraffic$estimation_method_detailed = addfWithoutTraffic$estimation_method_detailed

# UpdatedaddfWithoutTraffic = read.csv("C:/Users/User/Software/Traffic-Management-Predictive-Analysis/src/main/resources/UploadedCSVFiles/UpdatedaddfWithoutTraffic.csv")


# insertWithoutTraffic
query <- "INSERT INTO updated_addf_data_major(fd_PC,fd_2WMV,fd_car,fd_bus,fd_lgv,fd_hgvr2,fd_hgvr3,fd_hgvr4,fd_hgva3,fd_hgva5,fd_hgva6,fd_hgv,fd_all_mv,year,CP,estimation_method,estimation_method_detailed) VALUES("
count =0

for (i in 1:nrow(UpdatedaddfWithoutTraffic)) {
  
  query2 = paste(query, UpdatedaddfWithoutTraffic[i,1],",", UpdatedaddfWithoutTraffic[i,2],",", UpdatedaddfWithoutTraffic[i,3],",", 
                 UpdatedaddfWithoutTraffic[i,4],",", UpdatedaddfWithoutTraffic[i,5],",", UpdatedaddfWithoutTraffic[i,6],","
                 , UpdatedaddfWithoutTraffic[i,7],",", UpdatedaddfWithoutTraffic[i,8],",", UpdatedaddfWithoutTraffic[i,9],","
                 , UpdatedaddfWithoutTraffic[i,10],",", UpdatedaddfWithoutTraffic[i,11],",", UpdatedaddfWithoutTraffic[i,12],","
                 , UpdatedaddfWithoutTraffic[i,13],",", UpdatedaddfWithoutTraffic[i,14],",", UpdatedaddfWithoutTraffic[i,15],",'",
                    UpdatedaddfWithoutTraffic[i,16],"',\"", UpdatedaddfWithoutTraffic[i,17],"\")")
  inserted = sendUpdate(query2)
  count = count +1
  print(paste("count : ",count))
}






# Inserting With Traffic in two Parts
addfWithTraffic1st <- addfWithTraffic[1:(nrow(addfWithTraffic)/2),]
addfWithTraffic2nd <- addfWithTraffic[((nrow(addfWithTraffic)/2)+1) :nrow(addfWithTraffic) ,]
query <- "INSERT INTO updated_addf_data_major(year,CP,estimation_method,estimation_method_detailed,fd_PC,fd_2WMV,fd_car,fd_bus,fd_lgv,fd_hgvr2,fd_hgvr3,fd_hgvr4,fd_hgva3,fd_hgva5,fd_hgva6,fd_hgv,fd_all_mv) VALUES("

oldPer = 0
for (i in 1:nrow(addfWithTraffic2nd)) {
  
  query2 = paste(query, addfWithTraffic2nd[i,1],",", addfWithTraffic2nd[i,2],",'", addfWithTraffic2nd[i,3],"',\"", 
                 addfWithTraffic2nd[i,4],"\",", addfWithTraffic2nd[i,5],",", addfWithTraffic2nd[i,6],","
                 , addfWithTraffic2nd[i,7],",", addfWithTraffic2nd[i,8],",", addfWithTraffic2nd[i,9],","
                 , addfWithTraffic2nd[i,10],",", addfWithTraffic2nd[i,11],",", addfWithTraffic2nd[i,12],","
                 , addfWithTraffic2nd[i,13],",", addfWithTraffic2nd[i,14],",", addfWithTraffic2nd[i,15],",",
                 addfWithTraffic2nd[i,16],",", addfWithTraffic2nd[i,17],")")
  inserted = sendUpdate(query2)
  
  percent = round((i/nrow(addfWithTraffic2nd))*100,digits = 1)
  if(percent > oldPer){
    oldPer = percent
    print(paste("Percent : ",percent,"%",sep = ""))
    
  }
  
}










# # table(addf$estimation_method)
# 
# # GETTING NUM OF ZEROS OF EACH COLUMN.
# 
# # numofzeros = addf %>%filter(fd_all_mv == 0) %>% count()
# # numofzeros[1,1] / nrow(addf)
# # numofzeros[1,1]
# 
# 
# 
