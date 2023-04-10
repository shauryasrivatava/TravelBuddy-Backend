package com.travel.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class UserProfileDto {


    private String displayName;
    
    @Email(message = "Email address not valid !!")
	private String username;

    @NotNull
	private String password;
    
    @NotNull
	private String firstName;
    
    private String lastName;
    private String aboutMe;
    
    
    private Integer contact;
    private String gender;
    private String location;
    private Double totalExpenses;
}
