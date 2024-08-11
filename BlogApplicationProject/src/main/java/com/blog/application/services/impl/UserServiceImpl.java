package com.blog.application.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.application.exception.ResourceNotFoundException;
import com.blog.application.entity.User;
import com.blog.application.patloads.UserDto;
import com.blog.application.patloads.UserResponse;
import com.blog.application.repository.UserRepo;
import com.blog.application.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private  UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
        
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
      User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id",userId));
	
      user.setName(userDto.getName());
      user.setEmail(userDto.getEmail());
      user.setPassword(userDto.getPassword());
      user.setAbout(userDto.getAbout());
      user.setId(userDto.getId());
      User updatedUser = this.userRepo.save(user);
      UserDto userToDto1 = this.userToDto(updatedUser);
       
      return userToDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
     User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	@Override
	public UserResponse getAllUser(Integer pageNumber , Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> pageUser = this.userRepo.findAll(pageable);
		List<User> allUsers = pageUser.getContent();
		List<UserDto> userDtos = allUsers.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		
		
		
		 UserResponse userResponse = new UserResponse();
		
		userResponse.setContent(userDtos);
		userResponse.setPageNumber(pageUser.getNumber());
		userResponse.setPageSize(pageUser.getSize());
		userResponse.setTotalElements(pageUser.getTotalElements());
		userResponse.setTotalPages(pageUser.getTotalPages());
		userResponse.setLastPage(pageUser.isLast());

		
		return userResponse;
		
     
	}

	@Override
	public void deleteUser(Integer userId) {
    User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
	this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
	}
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user,UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());  
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
        return userDto;
	}

}
