package com.rajdeep.blogapi.daoRepositories;

import com.rajdeep.blogapi.modelEntity.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<CommentModel,Integer> {


}
