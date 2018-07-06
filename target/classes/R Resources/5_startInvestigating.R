

library(dplyr)
library(reshape)

query = "SELECT CP,year,traffic_capacity_ratio FROM updated_addf_data_major"
traffic = sendQuery(query)


cp <- traffic %>% filter(CP == 6001) 



year2016 <- traffic %>% filter(year == 2016)

justyearandall = traffic

md <- melt(justyearandall, id=(c("year", "CP")))

casted = cast(md,CP~year+variable)

v = as.numeric(casted[4,2:length(casted)])
timeSeries = ts(v, start = 2000,frequency = 1)
plot(timeSeries)
