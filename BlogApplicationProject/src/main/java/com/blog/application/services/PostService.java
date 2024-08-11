package com.blog.application.services;

import java.util.List;

import com.blog.application.patloads.PostDto;
import com.blog.application.patloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto , Integer userId , Integer categoryId);
	
	PostDto updatePost(PostDto postDto , Integer postId);
	
	void deletePost(Integer postId);
	
	 PostResponse getAllPost(Integer pageNumber , Integer pageSize , String sortBy , String sortDir); // for all post
	
	PostDto getPostById(Integer postId);// for single post
	
	List<PostDto> getPostByCategory(Integer categoryId); // get post by category
	
	List<PostDto> getPostByUser(Integer userId); // get post by user
	
	List<PostDto> searchPost(String keyword);


}
