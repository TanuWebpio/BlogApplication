package com.blog.application.services;


import com.blog.application.patloads.UserDto;
import com.blog.application.patloads.UserResponse;

public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user , Integer userId);
	UserDto getUserById(Integer user);
   UserResponse getAllUser(Integer pageNumber , Integer pageSize);
	void deleteUser (Integer userId);


}
