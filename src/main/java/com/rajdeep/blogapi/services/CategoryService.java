package com.rajdeep.blogapi.services;

import com.rajdeep.blogapi.payloadData.CategoryPayload;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public interface CategoryService {

    // methods inside interface are bydeafult public, so its not imp. to mention them public

    // create

    public CategoryPayload createCategory(CategoryPayload categoryPayload);


    // update  (new Category info, old category id)
    public CategoryPayload updateCategory(CategoryPayload categoryPayload,Integer categoryId);


    // delete

    public void deleteCategory(Integer categoryId);



    // get

    public CategoryPayload getCategory(Integer categoryId);


    // getAll

    public List<CategoryPayload> getAllCategory();


}
