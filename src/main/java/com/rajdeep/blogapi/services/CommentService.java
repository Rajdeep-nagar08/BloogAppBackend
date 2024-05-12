package com.rajdeep.blogapi.services;

import com.rajdeep.blogapi.payloadData.CommentPayload;
//import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;


@SpringBootApplication

public interface CommentService {


    // create Comment

    public CommentPayload createComment(CommentPayload commentPayload, Integer postId);



    // delete comment

    void deleteComment (Integer commentId);


    // update comment


}
