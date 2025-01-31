package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.CategoryDto;
import com.lcwd.electronic.store.dtos.PageableResponse;

public interface CategoryService {

    //create

    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto, String categoryId);


    //delete
    void delete(String categoryId);

    //get all
    PageableResponse<CategoryDto>  getAll(int pageNumebr, int pageSize, String sortby , String direction);


    // get single and
    CategoryDto getSingleCategory(String categoryId);
}
