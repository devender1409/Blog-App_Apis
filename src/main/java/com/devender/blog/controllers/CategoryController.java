package com.devender.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devender.blog.payloads.ApiResponse;
import com.devender.blog.payloads.CategoryDto;
import com.devender.blog.services.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping(value="/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cateoryDto){
		CategoryDto createdCategory = this.categoryService.createCategory(cateoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping(value="/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto cateoryDto,@PathVariable("categoryId") Integer catId ){
		CategoryDto updatedCategory = this.categoryService.updateCategory(cateoryDto,catId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	//get
	@GetMapping(value = "/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer catId){
		CategoryDto categoryDto = this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	//get all
	@GetMapping(value="/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	//delete
	@DeleteMapping(value="/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Catgory Deleted Successfully",true),HttpStatus.OK);
	}

}
