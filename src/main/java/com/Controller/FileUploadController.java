package com.Controller;


import com.Entity.DataSet;
import com.Service.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


// TODO miss Implementation in client
@RestController
@RequestMapping("/Upload")
public class FileUploadController {

    @Autowired
    DataSetService dataSetService;
//  Save File Details Like name, UploadDate, Size, ...
    @RequestMapping(value = "/SaveDetails",method = RequestMethod.POST)
    public int saveDataSetDetails(@RequestBody DataSet dataSet){
        return dataSetService.saveFileDetails(dataSet);
    }

//  Saving The Actual File In the Server
    @PostMapping(value = "/SaveFile") // //new annotation since 4.3
    public int singleFileUpload(@RequestParam("file") MultipartFile file) {
        return dataSetService.saveFile(file);
    }

//    Starting R Test Script.
//    TODO miss Implementation Of Possessing Real File Data.
    @GetMapping(value = "/StartR")
    public double startR(){
        return dataSetService.startR();
    }
}
