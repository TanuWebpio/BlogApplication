package com.blog.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.application.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment , Integer> {

}
