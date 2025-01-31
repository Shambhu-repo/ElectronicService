package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    //create
    ProductDto createProduct(ProductDto productDto);

    //update
    ProductDto updateProduct(ProductDto productDto, String productId);

    //delete
    public void deleteProduct(String productId);

    //get All

    //get single product
    ProductDto getSingleProduct(String productId);


    //get all : live
    PageableResponse<ProductDto> getAllProduct(int page, int pageSize, String sortBy, String sortOrder);


    //pageble return
    PageableResponse<ProductDto> getAllLiveProduct(int page, int pageSize, String sortBy, String sortOrder);


    //search product
    PageableResponse<ProductDto> searchByTitle(String subTitle, int page, int pageSize, String sortBy, String sortOrder);


}
