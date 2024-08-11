package com.blog.application.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.application.entity.Comment;
import com.blog.application.entity.Post;
import com.blog.application.exception.ResourceNotFoundException;
import com.blog.application.patloads.CommentDto;
import com.blog.application.repository.CommentRepo;
import com.blog.application.repository.PostRepo;
import com.blog.application.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
  // we need 3 repository
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).
				orElseThrow(()-> new ResourceNotFoundException("Post" , "post id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com = this.commentRepo.findById(commentId).
		orElseThrow(()-> new ResourceNotFoundException("Comment" , "comment id", commentId));
		this.commentRepo.delete(com);

		
	}

}
