package com.blog.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.application.entity.Category;
import com.blog.application.entity.Post;
import com.blog.application.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
//	Page findAll(Pageable p);
	//Page findAll(Pageable p);

}
