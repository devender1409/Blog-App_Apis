package com.devender.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devender.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
