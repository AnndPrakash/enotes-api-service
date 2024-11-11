package com.notes.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
		List<Category> category = categoryRepository.findByIsDeletedFalse();
		List<CategoryDto> categoryDto = category.stream().map(cat -> mapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		return categoryDto;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> category = categoryRepository.findByIsActiveTrueAndIsDeletedFalse();
		List<CategoryResponse> categoryList = category.stream().map(cat -> mapper.map(cat, CategoryResponse.class))
				.toList();

		return categoryList;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Optional<Category> findByCategory = categoryRepository.findByIdAndIsDeletedFalse(id);
		 if(findByCategory.isPresent()) {
			 Category category=findByCategory.get();
			 return mapper.map(category, CategoryDto.class);
		 }
		return null;
	}

	@Override
	public Boolean getDeleteCategoryById(Integer id) {
		Optional<Category> findByCategory = categoryRepository.findById(id);
		 if(findByCategory.isPresent()) {
			 Category category=findByCategory.get();
			  category.setIsDeleted(true);
			  categoryRepository.save(category);
			 return  true;
		 }
		return false;
	}

}
