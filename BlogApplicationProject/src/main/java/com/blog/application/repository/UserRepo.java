package com.blog.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.application.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
