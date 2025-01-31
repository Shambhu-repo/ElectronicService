package com.lcwd.electronic.store.controller;


import com.lcwd.electronic.store.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.ProductDto;
import com.lcwd.electronic.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductServiceController {

    @Autowired
    private ProductService productService;

 //create product
    @PostMapping("/createProduct")
public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
   ProductDto createdProduct =  productService.createProduct(productDto);
   return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
}

//update product
    @PutMapping("/updateProduct/{productId}")
public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable String productId) {
    ProductDto createdProduct = productService.updateProduct(productDto, productId);
    return new ResponseEntity<>(createdProduct, HttpStatus.OK);

}

  //get single product

    @GetMapping("/singleProduct/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable String productId) {
        ProductDto  getProduct =  productService.getSingleProduct(productId);
        return new ResponseEntity<>(getProduct, HttpStatus.OK);

    }

    //delete
    @DeleteMapping("deleteProduct/{productId}")
    public ResponseEntity<ApiResponseMessage> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
       ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder().message("Product deleted successfully !! ").success(true).status(HttpStatus.OK).build();
       return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }




    //get all product
    @GetMapping("/getAllProduct")
public ResponseEntity<PageableResponse<ProductDto>> getAllProducts
    (@RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
     @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
     @RequestParam(value = "sortBy", defaultValue = "title",required = false) String sortBy,
     @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
    )

     {
      PageableResponse<ProductDto>  products =  productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);
      return new ResponseEntity<>(products, HttpStatus.OK);

}



//

    @GetMapping("/liveProducts")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLiveProducts
            (@RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
             @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = "title",required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
            )

    {
        PageableResponse<ProductDto>  products =  productService.getAllLiveProduct(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    //search by titile
    @GetMapping("/searchbyKey/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> searchProductByKeyword
    (@RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
     @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
     @RequestParam(value = "sortBy", defaultValue = "title",required = false) String sortBy,
     @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir,
     @PathVariable String query
    )

    {
        PageableResponse<ProductDto>  products =  productService.searchByTitle(query, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

}
