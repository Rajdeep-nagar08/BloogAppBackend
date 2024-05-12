package com.rajdeep.blogapi.controller;


import com.rajdeep.blogapi.payloadData.ApiResponse;
import com.rajdeep.blogapi.payloadData.CategoryPayload;
import com.rajdeep.blogapi.services.CategoryService;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;



    // create category
    @PostMapping("/create")
    public ResponseEntity<CategoryPayload> createCategory(@RequestBody CategoryPayload categoryPayload){

        CategoryPayload createdCategory=categoryService.createCategory(categoryPayload);

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);

    }

    // update category

    @PutMapping("/update/{categoryId}")

    public ResponseEntity<CategoryPayload> updateCategory(@Valid @RequestBody CategoryPayload categoryPayload, @PathVariable ("categoryId") Integer catgId){

        CategoryPayload updatedCategory = categoryService.updateCategory(categoryPayload,catgId);

        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }


    // delete category

    @DeleteMapping("/delete/{categoryId}")

    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){

        categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(new ApiResponse("Category Delete",true),HttpStatus.OK);

    }


    // get category
    @GetMapping("/get/{categoryId}")

    public ResponseEntity<CategoryPayload> getCategory(@PathVariable Integer categoryId){

        CategoryPayload categoryPayload = categoryService.getCategory(categoryId);

        return new ResponseEntity<>(categoryPayload,HttpStatus.OK);

    }

    // get all categories

    @GetMapping("/getAll")

    public ResponseEntity<List<CategoryPayload>> getAllCategory(){

        List<CategoryPayload> categories = categoryService.getAllCategory();

        return new ResponseEntity<>(categories,HttpStatus.OK);
    }
}
