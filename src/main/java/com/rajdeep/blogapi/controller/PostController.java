package com.rajdeep.blogapi.controller;


import com.rajdeep.blogapi.config.AppConstants;
import com.rajdeep.blogapi.payloadData.ApiResponse;
import com.rajdeep.blogapi.payloadData.ModifiedPostPayload;
import com.rajdeep.blogapi.payloadData.PostPayload;
import com.rajdeep.blogapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    // create post

    @Autowired
    PostService postService;

    // create post
    @PostMapping ("/create/user/{userId}/category/{categoryId}")
    private ResponseEntity<PostPayload> createPost(@RequestBody PostPayload postPayload,
                                                   @PathVariable("userId") Integer uId,
                                                   @PathVariable("categoryId") Integer cId){

        PostPayload post= this.postService.createPost(postPayload,uId,cId);

        return new ResponseEntity<>(post, HttpStatus.CREATED);

    }

    // update post

    @PutMapping("/update/{postId}")

    private ResponseEntity<PostPayload> updatePost(@RequestBody PostPayload postPayload,
                                                   @PathVariable("postId") Integer pId
    ){

        PostPayload updatedPost= this.postService.updatePost(postPayload,pId);

        return new ResponseEntity<>(updatedPost,HttpStatus.OK);

    }

    // delete post

    @DeleteMapping("/delete/{postId}")

    private ResponseEntity<?> deletePost(@PathVariable("postId") Integer pId){

        this.postService.deletePost(pId);

        return new ResponseEntity<>(new ApiResponse("Post deleted !",true),HttpStatus.OK);

    }


    @GetMapping("/getAllPosts")

    private ResponseEntity<ModifiedPostPayload> getAllPosts(@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pgNo,
                                                            @RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pgSize,
                                                            @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){


        ModifiedPostPayload posts = this.postService.getAllPosts(pgNo,pgSize,sortBy,sortDir);
        return new ResponseEntity<ModifiedPostPayload>(posts,HttpStatus.OK);
    }

    // get post by post id
    @GetMapping("/get/{postId}")

    private ResponseEntity<PostPayload> getPostById(@PathVariable("postId") Integer pId){

        PostPayload post= this.postService.getPostById(pId);

        return new ResponseEntity<>(post,HttpStatus.OK);

    }

    // gets posts by category id
    @GetMapping("/get/category/{categoryId}")

    private ResponseEntity<List<PostPayload>> getPostsByCategory(@PathVariable("categoryId") Integer catgId){

        List<PostPayload> posts = this.postService.getPostsByCategory(catgId);

        return new ResponseEntity<>(posts,HttpStatus.OK);

    }

    // get posts by user ID

    @GetMapping("/get/user/{userId}")

    private ResponseEntity<List<PostPayload>> getPostsByUser(@PathVariable("userId") Integer uId){

        List<PostPayload> posts = this.postService.getPostsByUser(uId);

        return new ResponseEntity<>(posts,HttpStatus.OK);

    }

    @GetMapping("/search/{keyword}")

    private ResponseEntity<List<PostPayload>> searchPost(@PathVariable String keyword){

        List<PostPayload>posts=this.postService.searchPost(keyword);

        return new ResponseEntity<>(posts,HttpStatus.OK);

    }



}