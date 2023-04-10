package com.travel.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dao.CommentDao;
import com.travel.dao.TripDao;
import com.travel.dao.UserProfileDao;
import com.travel.exceptions.ResourceNotFoundException;
import com.travel.model.Comment;
import com.travel.model.Trip;
import com.travel.model.UserProfile;
import com.travel.payloads.CommentDto;
import com.travel.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	UserProfileDao userProfileDao;
	
	@Autowired
	TripDao tripDao;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer tripId) {
//		UserProfile userProfile = this.userProfileDao.findById(userProfileId).orElseThrow(()-> new ResourceNotFoundException("UserProfile","User id",userProfileId));
		Trip trip= this.tripDao.findById(tripId).orElseThrow(()-> new ResourceNotFoundException("Trip","Trip id", tripId));
				
				
		Comment comment=  this.modelMapper.map(commentDto ,Comment.class);
		comment.setTrip(trip);
		
		Comment newComment = this.commentDao.save(comment);
		
		return this.modelMapper.map(newComment, CommentDto.class);
	}


	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
		Comment comment= commentDao.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment","Comment id",commentId));
		comment.setComment(commentDto.getComment());
		commentDao.save(comment);
		return modelMapper.map(comment, CommentDto.class);
	}


	@Override
	public void deleteComment(Integer commentId) {
		Comment comment= commentDao.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment","Comment id",commentId));
		commentDao.delete(comment);
		
	}


	@Override
	public CommentDto getcommenById(Integer commentId) {
		// TODO Auto-generated method stub
		Comment comment= commentDao.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment","Comment id",commentId));
		
		return modelMapper.map(comment, CommentDto.class);
	}


	@Override
	public List<CommentDto> getAllComment() {
		List<Comment> allComments= commentDao.findAll();
		List<CommentDto> commentDtos = allComments.stream().map((comment)-> modelMapper.map(comment, CommentDto.class))
				.collect(Collectors.toList());
		
		return commentDtos;
	}

}
