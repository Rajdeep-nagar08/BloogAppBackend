package com.rajdeep.blogapi.services.impl;

import com.rajdeep.blogapi.daoRepositories.CommentDao;
import com.rajdeep.blogapi.daoRepositories.PostDao;
import com.rajdeep.blogapi.exceptionsHandlers.ResourceNotFoundException;
import com.rajdeep.blogapi.modelEntity.CommentModel;
import com.rajdeep.blogapi.modelEntity.PostModel;
import com.rajdeep.blogapi.payloadData.CommentPayload;
import com.rajdeep.blogapi.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostDao postDao;
    @Override
    public CommentPayload createComment(CommentPayload commentPayload, Integer postId) {

        PostModel postModel= this.postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post ID",postId));

        CommentModel commentModel=  this.modelMapper.map(commentPayload,CommentModel.class);

        commentModel.setPostMapped(postModel);

        CommentModel savedComment=this.commentDao.save(commentModel);

        return this.modelMapper.map(savedComment,CommentPayload.class);


    }

    @Override
    public void deleteComment(Integer commentId) {

        this.commentDao.deleteById(commentId);


    }
}
