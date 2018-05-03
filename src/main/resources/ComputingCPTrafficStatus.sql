UPDATE updated_addf_data_major 
SET 
    traffic_status = CASE
        WHEN traffic_capacity_ratio <= 0.5 THEN 1
        WHEN
            traffic_capacity_ratio > 0.5
                AND traffic_capacity_ratio <= 0.65
        THEN
            2
        WHEN
            traffic_capacity_ratio > 0.65
                AND traffic_capacity_ratio <= 0.8
        THEN
            3
        ELSE 4
    END;
                                    