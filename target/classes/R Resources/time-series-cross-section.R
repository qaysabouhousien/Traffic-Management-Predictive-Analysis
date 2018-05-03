library(pcse)
data("agl")
agl.lm <- lm(growth ~ lagg1 + opengdp + openex + openimp + central +
               + leftc + inter + as.factor(year), data = agl)
summary(agl.lm)


agl.pcse <- pcse(agl.lm, groupN = agl$country, groupT = agl$year)
summary(agl.pcse)


data("aglUn")
aglUn.lm <- lm(growth ~ lagg1 + opengdp + openex + openimp + central +
                    + leftc + inter + as.factor(year), data = aglUn)
aglUn.pcse1 <- pcse(aglUn.lm, groupN = aglUn$country,
                        groupT = aglUn$year, pairwise = TRUE)
summary(aglUn.pcse1)

