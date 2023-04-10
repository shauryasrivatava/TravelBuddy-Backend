package com.travel.service;

import java.util.List;

import com.travel.payloads.CommentDto;


public interface CommentService {
	
	public CommentDto createComment(CommentDto commentDto,  Integer tripId);
	
	public CommentDto updateComment(CommentDto commentDto, Integer commentID);
	
	public void deleteComment(Integer commentId);
	
	public CommentDto getcommenById(Integer commentId);
	
	public List<CommentDto> getAllComment();

}
