package com.lcwd.electronic.store.controller;

import com.lcwd.electronic.store.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.dtos.CategoryDto;
import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    //create

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDto>  createCategory(@RequestBody CategoryDto categoryDto) {
        //call service to save object
     CategoryDto categoryDto1 =    categoryService.create(categoryDto);
     return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);


    }


    //update
    @PutMapping("/update/{categoryId}")
public ResponseEntity<CategoryDto> updateCategory(
        @RequestBody CategoryDto categoryDto,
        @PathVariable String categoryId)
    {
      CategoryDto categoryDto1 =  categoryService.update(categoryDto, categoryId);
      return new ResponseEntity<>(categoryDto1, HttpStatus.OK);
}


    /// delete
    @DeleteMapping("/delete/{categoryId}")
public ResponseEntity<ApiResponseMessage>  deleteCategory(@PathVariable String categoryId) {
    categoryService.delete(categoryId);
    ApiResponseMessage apiResponseMessage =ApiResponseMessage.builder().message("Category deleted successfully !! ")
            .status(HttpStatus.OK).success(true).build();
    return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);}

    //get all

    @GetMapping("/getAll")
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategories(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false ) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "1", required = false ) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false ) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    )

    {
      PageableResponse<CategoryDto>  pageableResponse =  categoryService.getAll(pageNumber,pageSize,sortBy,sortDir);
      return new ResponseEntity<>(pageableResponse, HttpStatus.OK);


    }


    //get single
    @GetMapping("/singleCategory/{categoryId}")
    public ResponseEntity<CategoryDto> getSingle(@PathVariable String categoryId) {
       CategoryDto categoryDto = categoryService.getSingleCategory(categoryId);
       return new ResponseEntity<>(categoryDto, HttpStatus.OK);


    }

    //search by keyword



}
