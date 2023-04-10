package com.travel.payloads;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	
	@NotNull
	private String comment;
	
//	private UserProfileDto user;
	
	private TripDto trip;

}
