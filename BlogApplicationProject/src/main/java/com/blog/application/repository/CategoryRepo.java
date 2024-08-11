package com.blog.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.application.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>
{

}
