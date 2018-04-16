
library(RMySQL)
library(DBI)


con <- dbConnect(RMySQL::MySQL(),user="root", password='1234',
                 dbname='traffic_management_predictive_analytics', host='localhost')

dbListTables(con)





killDbConnections <- function () {

  all_cons <- dbListConnections(MySQL())

  print(all_cons)

  for(con in all_cons)
    +  dbDisconnect(con)

  print(paste(length(all_cons), " connections killed."))

}

sendQuery <- function (query) {

  # send Query to obtain result set
  rs <- dbSendQuery(con, query)

  # get elements from result sets and convert to dataframe
  result <- fetch(rs, -1)
  dbClearResult(rs)
  # return the dataframe
  return(result)
}
sendUpdate <- function(query){

  
  result <- dbSendStatement(con,query)
  affectedRows <- dbGetRowsAffected(result)
  dbClearResult(result)
  return(affectedRows)
}







