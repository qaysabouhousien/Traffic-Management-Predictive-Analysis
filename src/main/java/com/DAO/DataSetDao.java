package com.DAO;


import com.Entity.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataSetDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public int saveFileDetails(DataSet dataSet){
        final String query = "INSERT INTO dataset(file_name,admin_id) VALUES(?,?)";

        return jdbcTemplate.update(query,(preparedStatement -> {
          preparedStatement.setString(1,dataSet.getName());
          preparedStatement.setInt(2,dataSet.getAdminId());
        }));
    }

}
