package com.blog.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.application.patloads.CategoryDto;

@Service
public interface CategoryService {

	CategoryDto createCategory (CategoryDto categoryDto);
	
	CategoryDto updateCategory (CategoryDto categoryDto , Integer category);
	
	void deleteCategory(Integer categoryId);
	
    CategoryDto getCategory(Integer categoryId);
    
    List<CategoryDto> getCategories();
}
