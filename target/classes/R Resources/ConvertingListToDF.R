library(dplyr)
library(magrittr)


list_to_df <- function(listfordf){
  if(!is.list(listfordf)) stop("it should be a list")
  
  if(listfordf %>% names %>% is.null) {
    seq_along(listfordf) %>%
      data.frame(matname = ., stringsAsFactors = FALSE) %>%
      rowwise %>%
      do(list.element = listfordf %>% extract2(.$matname))
  } else {
    names(listfordf) %>%
      data.frame(matname = ., stringsAsFactors = FALSE) %>%
      group_by(matname) %>%
      do(comm.matrix = listfordf %>% extract2(.$matname))
  }
}
df <- list_to_df(RedCPs)
