package com.rajdeep.blogapi.services;

import com.rajdeep.blogapi.payloadData.ModifiedPostPayload;
import com.rajdeep.blogapi.payloadData.PostPayload;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public interface PostService {

    // create post, getting user and category of post via URL so adding them in arguments
    PostPayload createPost(PostPayload postPayload, Integer userId, Integer categoryId);


    // update post
    PostPayload updatePost(PostPayload postPayload, Integer postId);


    // delete post
    void deletePost(Integer postId);


    //get all posts
    ModifiedPostPayload getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // get single post
    PostPayload getPostById(Integer postId);

    // get all posts by category
    List<PostPayload> getPostsByCategory(Integer categoryId);


    // get all posts by user
    List<PostPayload> getPostsByUser(Integer userId);


    //search posts by keywords in title

    List<PostPayload> searchPost(String keyword);

}
