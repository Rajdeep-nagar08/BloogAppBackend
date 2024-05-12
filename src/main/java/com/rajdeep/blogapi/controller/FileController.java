package com.rajdeep.blogapi.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import com.rajdeep.blogapi.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@RestController
@RequestMapping("/file")


public class FileController {

    @Autowired
    private FileService fileService;

//    @Autowired
//    private PostService1 postService;


    // getting path from applications.properties,
    // this is the path of the folder where we want to save the file in our system
    @Value("${project.image}")
    private String path;



    // upload Image

    @PostMapping("/post/image/upload/{postId}")

    public String fileUpload(@RequestParam("image") MultipartFile image,
                                                  @PathVariable Integer postId) throws IOException {

//        PostPayload post = this.postService.getPostById(postId);
//
//        String fileName = this.fileService.uploadImage(path,image);
//
//        post.setImageName(fileName);
//
//        System.out.println("File Name=> "+fileName);
//
//        PostPayload updatedPost=this.postService.updatePost(post,postId);

//        PostPayload postPayload=new PostPayload();

//        return new ResponseEntity<>(updatedPost, HttpStatus.OK);

//        return new ResponseEntity<>(postPayload, HttpStatus.OK);

        return "Heelo";
    }


    // download Image, method to serve image


    // produce jpeg image
    @GetMapping(value = "/images/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    private void downloadFile(@PathVariable String imageName, HttpServletResponse response) throws IOException {

        InputStream resource = this.fileService.getResource(path,imageName);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(resource,response.getOutputStream());

    }

    // localhost:8080/images/abc.png


}
