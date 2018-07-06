
applyForecast <- function(traffic,cpNum){
  cp <- traffic %>% filter(CP == cpNum) 
  timeSeries = ts(cp[,"trafficCapacityRatio"], start = 2000,frequency = 1)
  time <- time(timeSeries)
  n.valid = 12
  ntrain = length(timeSeries) - n.valid
  train.ts = window(timeSeries,start=time[1], end=time[ntrain])
  valid.ts = window(timeSeries,start=time[ntrain+1],end=time[ntrain+n.valid] )
  
  fit <- auto.arima(train.ts,D = 1)
  res = forecast(fit,h=12)
  return(res)
}
