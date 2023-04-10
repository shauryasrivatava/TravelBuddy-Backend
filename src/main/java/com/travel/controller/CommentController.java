package com.travel.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.travel.payloads.ApiResponse;
import com.travel.payloads.CommentDto;
import com.travel.payloads.TripDto;
import com.travel.service.CommentService;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {
	
	@Autowired
	CommentService commentService;

	@PostMapping("/trip/{tripId}/comment")
	public ResponseEntity<CommentDto> addComment(@Valid
			@RequestBody CommentDto commentdto,
			@PathVariable Integer tripId
			){
		
		CommentDto createDto= commentService.createComment(commentdto,  tripId);
		return new ResponseEntity<CommentDto>(createDto,HttpStatus.OK);
	}
	
	@PutMapping("/comment/{commnetId}/")
	public ResponseEntity<CommentDto> updateComment(@Valid
			@RequestBody CommentDto commentDto,
			@PathVariable Integer tripId,
			@PathVariable Integer commentId){
		CommentDto updatedComment = commentService.updateComment(commentDto, commentId);
		return new ResponseEntity<CommentDto>(updatedComment,HttpStatus.OK);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ApiResponse deleteComment(
			@PathVariable Integer commentId){
		commentService.deleteComment(commentId);
		return new ApiResponse("Deleted Successfully !!", true);
	}
	
	@GetMapping("/comment/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable Integer commentId){
		CommentDto comment = commentService.getcommenById(commentId);
		
		return new ResponseEntity<CommentDto>(comment,HttpStatus.OK);
	}
	
	
	@GetMapping("/comment")
	public ResponseEntity<List<CommentDto>> getAllTrips(){
    	
    	List<CommentDto> comments= commentService.getAllComment();   	
    	
    	return new ResponseEntity<List<CommentDto>>(comments,HttpStatus.OK); 
   	}
	
}
