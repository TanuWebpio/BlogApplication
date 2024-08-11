package com.blog.application.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.application.config.AppContants;
import com.blog.application.patloads.ApiResponse;
import com.blog.application.patloads.PostDto;
import com.blog.application.patloads.PostResponse;
import com.blog.application.services.FileService;
import com.blog.application.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			){
		 PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		 return new ResponseEntity<PostDto>(createPost , HttpStatus.CREATED);
				 
		
	}
	// get by user
	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> post1 = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(post1,HttpStatus.OK);
	}
	
	// get by category
 
	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> post1 = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(post1,HttpStatus.OK);
	}
	//get all post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber" , defaultValue=AppContants.PAGE_NUMBER , required=false)Integer pageNumber,
			@RequestParam(value="pageSize" , defaultValue=AppContants.PAGE_SIZE , required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppContants.SORT_BY , required = false)String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppContants.SORT_DIR , required = false)String sortDir
)// sortDir = sortDirection , asc = ascending
	{
		 PostResponse allPost = this.postService.getAllPost(pageNumber , pageSize , sortBy , sortDir);
		return new ResponseEntity<PostResponse>(allPost , HttpStatus.OK);
	}
	
	//get all post by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto>getPostById(@PathVariable Integer postId){
		        PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	//delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is successfully  deleted !!" , true);
		
	}
	// update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Integer postId) {
		PostDto updatePost = this.postService.updatePost(postDto , postId);
		return new ResponseEntity<PostDto>(updatePost , HttpStatus.OK);
		
	}
	// Search method
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords")String keywords){
		List<PostDto> result = this.postService.searchPost(keywords);
		return new ResponseEntity<List<PostDto>>(result , HttpStatus.OK);
		
	}
	// post image upload
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image
			,@PathVariable Integer postId ) throws IOException{
		
		PostDto postDto = this.postService.getPostById(postId);
		
        String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost , HttpStatus.OK);
		
	}
	//method to serve file
	@GetMapping(value="post/image/{imageName}" , produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName")String imageName , HttpServletResponse response) 
					throws IOException{
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
	
	
	
}
