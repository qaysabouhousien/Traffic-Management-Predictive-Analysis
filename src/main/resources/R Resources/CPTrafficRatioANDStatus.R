
library(dplyr)
library(reshape)

# Getting What's usefull for analysis from db 
query = "SELECT CP,year, traffic_status FROM updated_addf_data_major"
addf = sendQuery(query)



castTheDF <- function(traffic){
  # Melting the df now we have years in columns.
  md <- melt(traffic, id=(c("year", "CP")))
  casted = cast(md,CP~year+variable)
  return(casted)
}
addf <- castTheDF(addf)



getUntilLevelCPs <- function(castedDataFrame,level){
  
  # Getting CPs which turn into <level> in the 17 years.
  levelTurn <- function(x,level){
    for(i in 1:length(x)){
      if(!is.na(x[i]) && x[i] == level){
        return(x[1:i])
      }  
    }
  }
  
  # This Line Creats a List and put null values where there is no <Level> in the CP Vector 
  CPs <- apply(castedDataFrame, 1, function(x) levelTurn(x,level))
 
   # Removing Null Values From the List
  CPs <-  CPs[lapply(CPs, length) > 2]
  
  
  RmNullValues <- function(x){
    x <- x[!is.na(x)]
    return(x)
  }
  CPs <- lapply(CPs ,function(x) RmNullValues(x))

  CPs <-  CPs[lapply(CPs, length) > 2]
  return(CPs)
}

RedCPs <- getUntilLevelCPs(addf,4)
OrangeCPs <- addf %>% filter(`2016_traffic_status` == 3)
YellowCPs <- addf %>% filter(`2016_traffic_status` == 2)




