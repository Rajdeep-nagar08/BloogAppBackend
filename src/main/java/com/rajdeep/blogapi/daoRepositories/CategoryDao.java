package com.rajdeep.blogapi.daoRepositories;

import com.rajdeep.blogapi.modelEntity.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<CategoryModel,Integer> {


}
