package com.rajdeep.blogapi.daoRepositories;

import com.rajdeep.blogapi.modelEntity.CategoryModel;
import com.rajdeep.blogapi.modelEntity.PostModel;
import com.rajdeep.blogapi.modelEntity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDao extends JpaRepository<PostModel,Integer> {

    List<PostModel> findByUserMapped(UserModel user);

    List<PostModel> findByCategoryMapped(CategoryModel category);


//     used to find all posts whose title containg the keyword
    List<PostModel> findByTitleContaining(String keyword);


}

