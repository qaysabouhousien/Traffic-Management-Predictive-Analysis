SELECT 
    cp, COUNT(*) AS cnt, MAX(year) AS max_year
FROM
    updated_addf_data_major
GROUP BY CP
HAVING MAX(year) != 2016
ORDER BY max_year DESC , cnt;