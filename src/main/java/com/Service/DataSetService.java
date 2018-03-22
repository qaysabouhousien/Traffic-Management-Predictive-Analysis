package com.Service;

        import com.DAO.DataSetDao;
        import com.Entity.DataSet;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import org.springframework.web.multipart.MultipartFile;

        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;

@Service
public class DataSetService {

    @Autowired
    DataSetDao dataSetDao;
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "src/main/resources/UploadedCSVFiles/";



    public int saveFileDetails(DataSet dataSet){

        return dataSetDao.saveFileDetails(dataSet);
    }

    public int saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return -1;
        }
        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();

            String completeData = new String(bytes);
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }
}
