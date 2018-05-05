
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
install.packages("randomForest")
training_set[which(),]
training_set <-  training_set[complete.cases(training_set), ]
test_set <- test_set[complete.cases(test_set),]
library(randomForest)
DT <- randomForest(x = training_set[-17],
                   y = training_set$`2016_traffic_capacity_ratio`,
                   ntree = 500)


plot(DT)
fancyRpartPlot(DT)

prediction <- predict(DT,test_set)
trainPred <- predict(DT,training_set)
submit <- data.frame(pred=trainPred)


test_set$Pred <- submit
training_set$Pred <- submit

v = test_set$`2016_traffic_capacity_ratio` - test_set$Pred
v2 = training_set$`2016_traffic_capacity_ratio` - training_set$Pred
