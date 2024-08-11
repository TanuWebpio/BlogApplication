//package com.blog.application.services.impl;
//
//import java.awt.print.Pageable;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.hibernate.query.Page;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//import com.blog.application.entity.Category;
//import com.blog.application.entity.Post;
//import com.blog.application.entity.User;
//import com.blog.application.exception.ResourceNotFoundException;
//import com.blog.application.patloads.PostDto;
//import com.blog.application.repository.CategoryRepo;
//import com.blog.application.repository.PostRepo;
//import com.blog.application.repository.UserRepo;
//import com.blog.application.services.PostService;
//
//@Service
//public class PostServiceImpl implements PostService {
//
//	private static final int S = 0;
//
//	@Autowired
//	private PostRepo postRepo;
//	
//	@Autowired
//	private ModelMapper modelMapper;
//	
//	@Autowired
//	private UserRepo userRepo;
//	
//	@Autowired
//	private CategoryRepo categoryRepo;
//	
//	@Override
//	public PostDto createPost(PostDto postDto , Integer userId , Integer categoryId) {
//		
//		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" , "User Id", userId));
//		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
//		
//		Post post = this.modelMapper.map(postDto, Post.class);
//		post.setImageName("default.png");
//		post.setAddedDate(new Date());
//		post.setUser(user);
//		post.setCategory(category);
//		
//		Post newPost = this.postRepo.save(post);
//		return this.modelMapper.map(newPost, PostDto.class);
//	}
//
//	@Override
//	public PostDto updatePost(PostDto postDto, Integer postId) {
//        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId)); 
//         post.setTitle(postDto.getTitle());
//         post.setContent(postDto.getContent());
//         post.setImageName(postDto.getImageName());
//         
//         Post updatedPost = this.postRepo.save(post);
//         
//         return this.modelMapper.map(updatedPost, PostDto.class);
//        
//	}
//
//	@Override
//	public void deletePost(Integer postId) {
//
//		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
//	             
//		this.postRepo.delete(post);
//	}
//
//	@Override
//	public <T> List<PostDto> getAllPost(Integer pageNumber, Integer pageSize) {
//	    Pageable p = (Pageable) PageRequest.of(pageNumber, pageSize);
////	    <Post> pagePost = this.postRepo.findAll(pageable);
//		   Page pagePost = this.postRepo.findAll(p);
//
//	    List<Post> allPosts = pagePost.getContent();
//	    
//	    List<PostDto> postDtos = allPosts.stream()
//	            .map(post -> this.modelMapper.map(post, PostDto.class))
//	            .collect(Collectors.toList());
//	    
//	    return postDtos;
//	}
//
//
//	@Override
//	public PostDto getPostById(Integer postId) {
//          Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId)); 
//		
//		return this.modelMapper.map(post,PostDto.class);
//	}
//
//	@Override
//	public List<PostDto> getPostByCategory(Integer categoryId) {
//		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new 
//				ResourceNotFoundException("Category", "category Id", categoryId));
//		List<Post> post1 = this.postRepo.findByCategory(cat);
//		
//		List<PostDto> postDtos = post1.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//		
//		return postDtos;
//		
//	}
//
//	@Override
//	public List<PostDto> getPostByUser(Integer userId) {
//		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user Id", userId));
//		List<Post> post1 = this.postRepo.findByUser(user);
//		List<PostDto> postDtos = post1.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//		
//		return postDtos;
//	}
//
//	@Override
//	public List<Post> searchPost(String keyword) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
package com.blog.application.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.application.entity.Category;
import com.blog.application.entity.Post;
import com.blog.application.entity.User;
import com.blog.application.exception.ResourceNotFoundException;
import com.blog.application.patloads.PostDto;
import com.blog.application.patloads.PostResponse;
import com.blog.application.repository.CategoryRepo;
import com.blog.application.repository.PostRepo;
import com.blog.application.repository.UserRepo;
import com.blog.application.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize , String sortBy , String sortDir) {
	  	Sort sort = (sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
//		if(sortDir.equalsIgnoreCase("asc")) {
//			sort = Sort.by(sortBy).ascending();
//		}else {
//			sort = Sort.by(sortBy).descending();
//		}
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize , sort);
		Page<Post> pagePost = this.postRepo.findAll(pageable);
		List<Post> allPosts = pagePost.getContent();
		
		List<PostDto> postDtos = allPosts.stream()
				.map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		 PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		
		List<PostDto> postDtos = posts.stream()
				.map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> postDtos = posts.stream()
				.map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
           List<Post> posts  = this.postRepo.findByTitleContaining(keyword);
           List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}
}

