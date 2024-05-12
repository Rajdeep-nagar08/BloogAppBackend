package com.rajdeep.blogapi.services.impl;

import com.rajdeep.blogapi.daoRepositories.CategoryDao;
import com.rajdeep.blogapi.daoRepositories.PostDao;
import com.rajdeep.blogapi.daoRepositories.UserDao;
import com.rajdeep.blogapi.exceptionsHandlers.ResourceNotFoundException;
import com.rajdeep.blogapi.modelEntity.CategoryModel;
import com.rajdeep.blogapi.modelEntity.PostModel;
import com.rajdeep.blogapi.modelEntity.UserModel;
import com.rajdeep.blogapi.payloadData.ModifiedPostPayload;
import com.rajdeep.blogapi.payloadData.PostPayload;
import com.rajdeep.blogapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PostPayload createPost(PostPayload postPayload, Integer userId, Integer categoryId) {

        UserModel user= this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));

        CategoryModel category = this.categoryDao.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));

        PostModel post = this.modelMapper.map(postPayload,PostModel.class);

        post.setImageName("default.png");

        post.setAddedDate(new Date());

        post.setUserMapped(user);

        post.setCategoryMapped(category);

        PostModel savedPost = this.postDao.save(post);

        return this.modelMapper.map(savedPost,PostPayload.class);

    }

    @Override
    public PostPayload updatePost(PostPayload postPayload, Integer postId) {

        PostModel oldPost = this.postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostID",postId));

        // date is set in controller, not coming in payload

        oldPost.setTitle(postPayload.getTitle());
        oldPost.setContent(postPayload.getContent());
        oldPost.setImageName(postPayload.getImageName());

        PostModel updatedPost=this.postDao.save(oldPost);

        return this.modelMapper.map(updatedPost,PostPayload.class);

    }

    @Override
    public void deletePost(Integer postId) {

        this.postDao.deleteById(postId);

    }

    @Override
    public ModifiedPostPayload getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        // using pagination and sorting

        Sort sort=null;

        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }
        else{
            sort=Sort.by(sortBy).descending();
        }


        Pageable p= PageRequest.of(pageNumber,pageSize, sort);  // pageable object
//        Sort.by(sortBy).descending() for sorting in descending order by "sortBy"

        Page<PostModel> pagePost= this.postDao.findAll(p); // passing pageable object to findAll

        List<PostModel> allPosts = pagePost.getContent();

        List<PostPayload> allPostsPayloads = new ArrayList<>();

        for(PostModel pt: allPosts){
            allPostsPayloads.add(this.modelMapper.map(pt,PostPayload.class));
        }

        ModifiedPostPayload modifiedPostPayload= new ModifiedPostPayload();
        modifiedPostPayload.setContent(allPostsPayloads);
        modifiedPostPayload.setPageNumber(pagePost.getNumber());
        modifiedPostPayload.setPageSize(pagePost.getSize());
        modifiedPostPayload.setTotalElements(pagePost.getTotalElements());
        modifiedPostPayload.setLastPage(pagePost.isLast());
        modifiedPostPayload.setTotalPages(pagePost.getTotalPages());
        return modifiedPostPayload;

    }

    @Override
    public PostPayload getPostById(Integer postId) {

        PostModel post = this.postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post ID",postId));

        return this.modelMapper.map(post,PostPayload.class);
    }

    @Override
    public List<PostPayload> getPostsByCategory(Integer categoryId) {

        CategoryModel catg= this.categoryDao.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category ID",categoryId));

        List<PostModel> posts =this.postDao.findByCategoryMapped(catg);

        List<PostPayload> postPayloads = new ArrayList<>();

        for (PostModel post: posts){
            postPayloads.add(this.modelMapper.map(post,PostPayload.class));
        }


        return postPayloads;
    }

    @Override
    public List<PostPayload> getPostsByUser(Integer userId) {

        UserModel user = this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User ID",userId));

        List<PostModel> posts = this.postDao.findByUserMapped(user);

        List<PostPayload> postPayloads = new ArrayList<>();

        for (PostModel post: posts){
            postPayloads.add(this.modelMapper.map(post,PostPayload.class));
        }

        return postPayloads;
    }

    @Override
    public List<PostPayload> searchPost(String keyword) {

        List<PostModel> posts=this.postDao.findByTitleContaining(keyword);

        List<PostPayload> postPayloads=new ArrayList<>();

        for (PostModel postModel: posts){
            postPayloads.add(this.modelMapper.map(postModel,PostPayload.class));
        }

        return postPayloads;
    }


}
