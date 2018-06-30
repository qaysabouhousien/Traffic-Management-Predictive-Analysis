

library(dplyr)
library(reshape)

query = "SELECT CP,year, traffic_capacity_ratio FROM updated_addf_data_major WHERE year != 2017"
dataset <- sendQuery(query) 

castTheDF <- function(dataset){
  # Melting the df now we have years in columns.
  md <- melt(dataset, id=(c("year", "CP")))
  casted = cast(md,CP~year+variable)
  return(casted)
}



dataset <- castTheDF(dataset)

dataset <- dataset %>% filter(!is.na(`2016_traffic_capacity_ratio`)) 

# Splitting the dataset into the Training set and Test set 
library(caTools)
set.seed(101)
split = sample.split(dataset$`2016_traffic_capacity_ratio`, SplitRatio = 0.7)
training_set = subset(dataset, split == TRUE)
test_set = subset(dataset, split == FALSE)
  

CPS <- training_set[,1]
training_set[,1] <- NULL
# training_set[,1:10] <- NULL
library(randomForest)
training_set <-  training_set[complete.cases(training_set), ]
test_set <- test_set[complete.cases(test_set),]
startTime = Sys.time()
DT <- randomForest(x = training_set[-17],
                   y = training_set$`2016_traffic_capacity_ratio`,
                   ntree = 20)
endTime = Sys.time() - startTime

plot(DT, main = "Model Error - 20 Tree")
fancyRpartPlot(DT)

prediction <- predict(DT,test_set)
trainPred <- predict(DT,training_set)
submit <- data.frame(pred=trainPred)


test_set$Pred <- prediction
training_set$Pred <- submit

v = test_set$`2016_traffic_capacity_ratio` - test_set$Pred
v2 = training_set$`2016_traffic_capacity_ratio` - training_set$Pred
caret::RMSE(test_set$Pred,test_set$`2016_traffic_capacity_ratio`)
