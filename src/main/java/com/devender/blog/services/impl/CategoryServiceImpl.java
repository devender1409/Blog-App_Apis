package com.devender.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devender.blog.entities.Category;
import com.devender.blog.exceptions.ResourceNotFoundException;
import com.devender.blog.payloads.CategoryDto;
import com.devender.blog.repositories.CategoryRepo;
import com.devender.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category addedCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId) {
		Category tobeUpdatedCategory = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", " Category Id ", categoryId));
		tobeUpdatedCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		tobeUpdatedCategory.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = this.categoryRepo.save(tobeUpdatedCategory);
		
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category toBeDeletedCategory = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category"," category id ", categoryId));
		this.categoryRepo.delete(toBeDeletedCategory);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", " category id ", categoryId));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		
		List<CategoryDto> categoryDtos = categories.stream().map(category->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}
	
	
}
