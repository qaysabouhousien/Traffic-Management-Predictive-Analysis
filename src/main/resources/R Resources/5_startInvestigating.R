

library(dplyr)
library(reshape)

query = "SELECT * FROM major_roads_row_count_data"
traffic = sendQuery(query)


cp <- traffic %>% filter(CP == 6001) %>% filter(iDir == "N")


plot(cp[,"year"],cp[,"ALLMV"])

year2016 <- traffic %>% filter(year == 2016)

justyearandall = traffic[,c("year","CP","AMV")]

md <- melt(justyearandall, id=(c("year", "CP")))

casted = cast(md,CP~year+variable)

v = as.numeric(casted[1,2:length(casted)])
timeSeries = ts(v, start = 2000,frequency = 1)
plot(timeSeries)
