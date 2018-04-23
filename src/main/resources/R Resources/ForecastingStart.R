library(forecast)
library(pls)
query = "SELECT data.*, point.number_of_lanes FROM major_roads_row_count_data data inner join major_road_counting_point point
                  on data.cp = point.cp "
traffic = sendQuery(query)

traffic$trafficCapacityRatio = traffic$AMV/(traffic$number_of_lanes*1900)


cps = traffic %>% group_by(CP) %>% count()  %>% arrange(desc(n)) %>% filter(n== 216)


cp <- traffic %>% filter(CP == 6001) %>% filter(iDir == "N")




timeSeries = ts(cp[,"trafficCapacityRatio"], start = 2000,frequency = 12)
plot(timeSeries)
time <- time(timeSeries)
n.valid = 12
ntrain = length(timeSeries) - n.valid
train.ts = window(timeSeries,start=time[1], end=time[ntrain])
valid.ts = window(timeSeries,start=time[ntrain+1],end=time[ntrain+n.valid] )

fit <- auto.arima(train.ts,D = 1)
plot(timeSeries)
plot(forecast(fit,h=12))

