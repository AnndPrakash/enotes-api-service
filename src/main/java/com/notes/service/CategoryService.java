package com.notes.service;

import java.util.List;

import com.notes.dtos.CategoryDto;
import com.notes.dtos.response.CategoryResponse;

public interface CategoryService {
	public Boolean saveCategory(CategoryDto category);
	
	public List<CategoryDto>getAllCategory();

	public List<CategoryResponse> getActiveCategory();

	public CategoryDto getCategoryById(Integer id);

	public Boolean getDeleteCategoryById(Integer id);

}
