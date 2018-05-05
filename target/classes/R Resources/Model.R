
library(dplyr)
library(reshape)

query = "SELECT CP,year, traffic_capacity_ratio FROM updated_addf_data_major"
dataset <- sendQuery(query) 

castTheDF <- function(traffic){
  # Melting the df now we have years in columns.
  md <- melt(traffic, id=(c("year", "CP")))
  casted = cast(md,CP~year+variable)
  return(casted)
}


dataset <- castTheDF(dataset)
missingData <-  dataset[!complete.cases(dataset), ]

dataset <-  dataset[complete.cases(dataset), ]

CPS <- dataset[1]
dataset[1] <- NULL
colnames(dataset) <- c(1:length(dataset))
library(randomForest)
start_time <- Sys.time()
forest <- randomForest(x = dataset[,c(1:16)],
                       y = dataset$`17`,
                      ntree = 500)
end_time <- Sys.time()
print(end_time - start_time)

modelSample <- dataset[-1]
colnames(modelSample) <- c(1:length(modelSample))
modelSample["17"] <- NA
modelSample$`17` <- c(1:nrow(modelSample))

prediction <- predict(forest,modelSample)

modelSample[,"17"] <- prediction

modelSample$CP <- CPS[,1]
modelSample <- modelSample[,17:18]

insertQuery <- "INSERT INTO updated_addf_data_major
                (year,CP,estimation_method,estimation_method_detailed,traffic_capacity_ratio)
                VALUES ("
insertPred <- function(x){
 q <- paste(insertQuery,"2017",",",x[2],",'Model Prediction','Random Forest Prediction',",x[1] ,")")
 sendUpdate(q)
}

apply(modelSample, 1, function(x) insertPred(x))

