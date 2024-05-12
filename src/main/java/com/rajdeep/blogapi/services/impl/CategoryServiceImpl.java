package com.rajdeep.blogapi.services.impl;

import com.rajdeep.blogapi.daoRepositories.CategoryDao;
import com.rajdeep.blogapi.exceptionsHandlers.ResourceNotFoundException;
import com.rajdeep.blogapi.modelEntity.CategoryModel;
import com.rajdeep.blogapi.payloadData.CategoryPayload;
import com.rajdeep.blogapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryDao categoryDao;


    @Autowired
    ModelMapper modelMapper;



    @Override
    public CategoryPayload createCategory(CategoryPayload categoryPayload) {

        CategoryModel category = this.modelMapper.map(categoryPayload,CategoryModel.class);

        CategoryModel savedCategory=this.categoryDao.save(category);

        return this.modelMapper.map(savedCategory,CategoryPayload.class);

    }

    @Override
    public CategoryPayload updateCategory(CategoryPayload categoryPayload, Integer categoryId) {

        CategoryModel category= categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        category.setCategoryTitle(categoryPayload.getCategoryTitle());

        category.setCategoryDescription((categoryPayload.getCategoryDescription()));

        return this.modelMapper.map(category,CategoryPayload.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        categoryDao.deleteById(categoryId);

    }

    @Override
    public CategoryPayload getCategory(Integer categoryId) {

        CategoryModel category = categoryDao.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));

        return this.modelMapper.map(category,CategoryPayload.class);

    }

    @Override
    public List<CategoryPayload> getAllCategory() {

        List<CategoryModel> categories= categoryDao.findAll();

        List<CategoryPayload> categoryPayloads = new ArrayList<>();

        for (CategoryModel cat: categories){
            categoryPayloads.add(this.modelMapper.map(cat,CategoryPayload.class));
        }

        return categoryPayloads;
    }


}
