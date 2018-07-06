

library(dplyr)
library(DMwR)

query = "SELECT * FROM Traffic"

traffic = sendQuery(query)



# PreProcessing 


# Extract Real Values 
# Mesaure Unit : KM 
traffic[,3:15] = traffic[,3:15] * 1000

#  Replacing Zero Values With NA
traffic[traffic == 0] <- NA 

# Removing the NA Values 
dfwithoutNA = na.omit(traffic)

# Fill in NA values with the values of the nearest neighbours
# THIS PROSSES TOOK A LOT OF TIME, ABOVE 3 HOURS!!
updateddata  = knnImputation(data = traffic,distData = dfwithoutNA)

# Saving backup file.
write.csv(updateddata,"handlingMissingValues.csv",row.names = FALSE)

# Rounding to beautiful Numbers
updateddata <- round(updateddata,1)

# Update ALLMV And HGV Columns with the updated sum.
updatedALLMV = apply(updateddata,1,function(x)  sum(x[4:13]))
updateddata[,"ALLMV"] <- updatedALLMV
updatedHGV = apply(updateddata, 1, function(x) sum(x[8:13]))  
updateddata[,"hgv"] <- updatedHGV

# inserting the updated table into a new table in the DB. 
# THIS PROSSESS TAKES ABOUT 5 HOURS TO COMPLETE!!
query <- "INSERT INTO updated_traffic(year,CP,PC,2WMV,car,bus,lgv,hgvr2,hgvr3,hgvr4,hgva3,hgva5,hgva6,hgv,allmv) VALUES("
count =0

for (i in 1:nrow(updateddata)) {
  
  query2 = paste(query, updateddata[i,1],",", updateddata[i,2],",", updateddata[i,3],",", 
                updateddata[i,4],",", updateddata[i,5],",", updateddata[i,6],","
                , updateddata[i,7],",", updateddata[i,8],",", updateddata[i,9],","
                , updateddata[i,10],",", updateddata[i,11],",", updateddata[i,12],","
                , updateddata[i,13],",", updateddata[i,14],",", updateddata[i,15],")")
  inserted = sendUpdate(query2)
  count = count +1
  print(paste("count : ",count))
  print(paste("inserted : ",inserted))
}


      
corrVars = updateddata[3:13]
corrMatrix = round(cor(corrVars),3)
write.csv(corrMatrix ,"correlation.csv")
