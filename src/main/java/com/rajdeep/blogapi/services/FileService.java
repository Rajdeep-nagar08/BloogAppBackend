package com.rajdeep.blogapi.services;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


@SpringBootApplication
public interface FileService {

    // getting file from "file" object and saving it in our system in folder at path= "path"

    // if we want to save file in database, then implementation will be different.

    String uploadImage(String path, MultipartFile file) throws IOException;


    // to serve  or download file
    // path = file is stored at this path
    // fileName = name of the file
    InputStream getResource(String path, String fileName) throws FileNotFoundException;



}
