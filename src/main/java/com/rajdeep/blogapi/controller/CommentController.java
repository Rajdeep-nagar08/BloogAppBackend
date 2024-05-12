package com.rajdeep.blogapi.controller;


import com.rajdeep.blogapi.payloadData.ApiResponse;
import com.rajdeep.blogapi.payloadData.CommentPayload;
import com.rajdeep.blogapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PutMapping("/add/postId/{postId}")
    private ResponseEntity<CommentPayload> createComment(@PathVariable("postId") Integer pId,
                                                         @RequestBody CommentPayload commentPayload){

      CommentPayload comment=  this.commentService.createComment(commentPayload,pId);

      return new ResponseEntity<>(comment, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{commentId}")

    private ResponseEntity<?> deleteComment(@PathVariable Integer commentId){

        this.commentService.deleteComment(commentId);

        return new ResponseEntity<>(new ApiResponse("Comment Deleted !",true),HttpStatus.OK);

    }

}
