package com.notes.service;

import java.util.List;

import com.notes.entities.Category;

public interface CategoryService {
	public Boolean saveCategory(Category category);
	
	public List<Category>getAllCategory();

}
