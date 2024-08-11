package com.blog.application.controller;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.patloads.ApiResponse;
import com.blog.application.patloads.UserDto;
import com.blog.application.patloads.UserResponse;
import com.blog.application.services.UserService;

import jakarta.validation.Valid;

	@RestController // controller of rest api
	@RequestMapping("/api/users")

	public class UserController {

		@Autowired
		private UserService userService;
		 @PostMapping("/")
		 public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
			 UserDto createUserDto = this.userService.createUser(userDto);
			 return new ResponseEntity<>(createUserDto , HttpStatus.CREATED);
		 }
		 @PutMapping("/{userId}")
		 public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId")Integer uid){
			UserDto updateUser = this.userService.updateUser(userDto, uid); 
			return ResponseEntity.ok(updateUser);
		 }
		 @DeleteMapping("/{userId}")
		 public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId")Integer uid){
			 this.userService.deleteUser(uid);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
		 }
		 @GetMapping("/")
		 public ResponseEntity<UserResponse> getAllUser(@RequestParam(value="pageNumber" , defaultValue="0" , required=false)Integer pageNumber,
					@RequestParam(value="pageSize" , defaultValue="10" , required=false)Integer pageSize){
			 UserResponse allUser = this.userService.getAllUser(pageNumber , pageSize);
				return new ResponseEntity<UserResponse>(allUser , HttpStatus.OK);
			// return ResponseEntity.ok(this.userService.getAllUser());
		 }
		 @GetMapping("/{userId}")
		 public ResponseEntity<UserDto>getSingleUser(@PathVariable Integer userId){
			 return ResponseEntity.ok(this.userService.getUserById(userId));
		 }
		
	

}

