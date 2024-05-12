package com.rajdeep.blogapi.services.impl;

import com.rajdeep.blogapi.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // getting file from "file" and saving it in the folder at path= "path"

        // Fetching filename

        System.out.println("Path ="+path);

        String name=file.getOriginalFilename();

        System.out.println("name="+name);

        // generating unique random name for the file

        String randomID= UUID.randomUUID().toString();

        String fileName1= randomID.concat(name.substring(name.lastIndexOf(".")));

        // Creating full Path

        String filepath= path+ File.separator+fileName1;   // File.seprator == "/"

        // creating folder if not created

        File f=new File(path);

        if(!f.exists()){
            f.mkdir();
        }

        // copy file to full path

        // source and destination

        Files.copy(file.getInputStream(), Paths.get(filepath));


        return fileName1;


    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fullPath = path+File.separator+fileName;

        InputStream inputStream = new FileInputStream(fullPath);

        // db logic to return input stream

        return inputStream;

    }
}
