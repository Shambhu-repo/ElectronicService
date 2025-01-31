package com.lcwd.electronic.store.services.implementation;

import com.lcwd.electronic.store.dtos.CategoryDto;
import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.entities.Category;
import com.lcwd.electronic.store.exception.ResourceNotFounException;
import com.lcwd.electronic.store.helper.Helper;
import com.lcwd.electronic.store.repositories.CategoryRepository;
import com.lcwd.electronic.store.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor

public class CategoryServiceImplementation implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        //creating category id randomly
        String userId = UUID.randomUUID().toString();
        categoryDto.setCategoryId(userId);

        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);

    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFounException("Category not found with given id"));

        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);


    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFounException("Category not found with given id"));

        categoryRepository.delete(category);


    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumebr, int pageSize, String sortby, String direction) {

        Sort sort = (direction.equalsIgnoreCase("desc:"))
                ? (Sort.by(sortby).descending())
                : (Sort.by(sortby).ascending());

        Pageable pageable = PageRequest.of(pageNumebr, pageSize, sort);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);
        return pageableResponse;


    }

    @Override
    public CategoryDto getSingleCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFounException("Category not found with given id"));


        return modelMapper.map(category, CategoryDto.class);
    }
}
