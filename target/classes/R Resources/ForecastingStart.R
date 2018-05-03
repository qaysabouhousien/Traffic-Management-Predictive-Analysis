
library(dplyr)
library(forecast)

library(pls)
query = "SELECT data.*, point.number_of_lanes FROM updated_addf_data_major data
          inner join major_road_counting_point point
                  on data.cp = point.cp "
traffic = sendQuery(query)


traffic$trafficCapacityRatio = traffic$all_mv/(traffic$number_of_lanes*1900*24)
traffic %>% group_by(CP) %>%  filter(trafficCapacityRatio >1)
cps = traffic %>% group_by(CP) %>% count()  %>% arrange(desc(n)) 

# applyForecast(traffic,"51")

cp <- traffic %>% filter(CP == 501) 
timeSeries = ts(cp[,"trafficCapacityRatio"], start = 2000,frequency = 1)
time <- time(timeSeries)
n.valid = 2
ntrain = length(timeSeries) - n.valid
train.ts = window(timeSeries,start=time[1], end=time[ntrain])
valid.ts = window(timeSeries,start=time[ntrain+1],end=time[ntrain+n.valid] )

fit <- auto.arima(train.ts,D = 1)
res = forecast(fit,h=3)
plot(res)
res







