package com.travel.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TripDto {

	private String title;
	private String content;
	private String imageName;
	private String invoiceName;
	private String Location;
	private double tripExpense;
	private UserProfileDto user;
}
