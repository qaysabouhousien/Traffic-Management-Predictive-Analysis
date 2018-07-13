package com.DAO;


import com.Entity.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Repository for handling Db Actions on the dataset table
 * @author - Qays
 */
@Repository
public class DataSetDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Saving file details in the database
     * @param dataSet Data set details
     * @return 1 on success/ -1 on failure / 0 on not found.
     */
    public int saveFileDetails(DataSet dataSet){
        final String query = "INSERT INTO dataset(file_name,admin_id) VALUES(?,?)";

        return jdbcTemplate.update(query,(preparedStatement -> {
          preparedStatement.setString(1,dataSet.getName());
          preparedStatement.setInt(2,dataSet.getAdminId());
        }));
    }

}
