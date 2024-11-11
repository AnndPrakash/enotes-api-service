package com.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.dtos.CategoryDto;
import com.notes.dtos.response.CategoryResponse;
import com.notes.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/save-category")
	public ResponseEntity<?> savedCategory(@RequestBody CategoryDto categoryDto) {
		Boolean saveCategory = categoryService.saveCategory(categoryDto);
		if (saveCategory) {
			return new ResponseEntity<>("saved Sucess ", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Not saved ", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/category")
	public ResponseEntity<?> getAllCategory() {
		List<CategoryDto> allCategory = categoryService.getAllCategory();
		if (CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}
		
		@GetMapping("/active-category")
		public ResponseEntity<?> getActiveCategory() {
			List<CategoryResponse> allCategory = categoryService.getActiveCategory();
			if (CollectionUtils.isEmpty(allCategory)) {
				return ResponseEntity.noContent().build();
			} else {
				return new ResponseEntity<>(allCategory, HttpStatus.OK);
			}
	
}       @GetMapping("/{id}")
		public ResponseEntity<?>getCategoryDetialsById(@PathVariable Integer id){
			CategoryDto categoryDto=categoryService.getCategoryById(id);
		     if(ObjectUtils.isEmpty(categoryDto)) {
		    	 return new ResponseEntity<>("Category Not Found with id ::"+id,HttpStatus.NOT_FOUND);
		     }
		     return new ResponseEntity<>(categoryDto,HttpStatus.OK);
		}

@DeleteMapping("/{id}")
public ResponseEntity<?>DeleteCategoryDetialsById(@PathVariable Integer id){
	Boolean delted=categoryService.getDeleteCategoryById(id);
     if(delted) {
    	 return new ResponseEntity<>("Category Deleted Successfully ::",HttpStatus.OK);
     }
     return new ResponseEntity<>("Category Not Deleted",HttpStatus.INTERNAL_SERVER_ERROR);
}



	}