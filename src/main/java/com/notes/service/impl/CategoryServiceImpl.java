package com.notes.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.notes.dtos.CategoryDto;
import com.notes.dtos.response.CategoryResponse;
import com.notes.entities.Category;
import com.notes.repository.CategoryRepository;
import com.notes.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {

//		Category category = new Category();
//		category.setName(categoryDto.getName());
//		category.setDescription(categoryDto.getDescription());
//		category.setIsActive(categoryDto.getIsActive());
		Category category = mapper.map(categoryDto, Category.class);
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		Category saveCategory = categoryRepository.save(category);
		if (ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> category = categoryRepository.findAll();
		List<CategoryDto> categoryDto = category.stream().map(cat -> mapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		return categoryDto;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> category = categoryRepository.findByIsActiveTrue();
		List<CategoryResponse> categoryList = category.stream().map(cat -> mapper.map(cat, CategoryResponse.class))
				.toList();

		return categoryList;
	}

}
