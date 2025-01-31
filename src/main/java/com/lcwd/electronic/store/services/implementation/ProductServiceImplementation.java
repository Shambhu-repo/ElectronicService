package com.lcwd.electronic.store.services.implementation;

import ch.qos.logback.core.CoreConstants;
import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.ProductDto;
import com.lcwd.electronic.store.entities.Product;
import com.lcwd.electronic.store.exception.ResourceNotFounException;
import com.lcwd.electronic.store.helper.Helper;
import com.lcwd.electronic.store.repositories.ProductRepository;
import com.lcwd.electronic.store.services.ProductService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service

public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        String productId = UUID.randomUUID().toString();
        productDto.setProductId(productId);

        ZonedDateTime zndt = ZonedDateTime.now(ZoneId.of("America/New_York"));

        Date date = Date.from(zndt.toInstant());
        productDto.setAddedDate(date);

        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
        return modelMapper.map(product, ProductDto.class);


    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFounException("Product not found given id !!"));

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());

        Product upodatedProduct = productRepository.save(product);
        return modelMapper.map(upodatedProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFounException("Product not found given id !!"));
        productRepository.delete(product);
    }


    @Override
    public ProductDto getSingleProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFounException("Product not found given id !!"));
        return modelMapper.map(product, ProductDto.class);
    }


    @Override
    public PageableResponse<ProductDto> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepository.findAll(pageable);
        return Helper.getPageableResponse(page, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllLiveProduct(int pageSize, int pageNumber, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepository.findByLiveTrue(pageable);

        return Helper.getPageableResponse(page, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepository.findByTitle(subTitle, pageable);
        return Helper.getPageableResponse(page, ProductDto.class);
    }


}
