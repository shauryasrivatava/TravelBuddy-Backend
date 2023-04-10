package com.travel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.model.UserProfile;
import com.travel.payloads.ApiResponse;
import com.travel.payloads.UserProfileDto;
import com.travel.service.UserProfileService;


@RestController
@RequestMapping("/api/v1/userProfiles")
public class UserProfileController {

	
	@Autowired
	
	private UserProfileService userProfileService;

	

    // PUT- update user
    @PutMapping("/{userProfileId}")
    public ResponseEntity<UserProfileDto> updateUserProfile(@Valid @RequestBody UserProfileDto userProfileDto, @PathVariable("userProfileId") Integer userProfileId) {
        UserProfileDto updatedUserProfile = this.userProfileService.updateUserProfile(userProfileDto, userProfileId);
        return ResponseEntity.ok(updatedUserProfile);
    }

    //ADMIN
    // DELETE -delete user
    //
    @DeleteMapping("/{userProfileId}")
    public ResponseEntity<ApiResponse> deleteUserProfile(@PathVariable("userProfileId") Integer userProfileId) {
        this.userProfileService.deleteUserProfile(userProfileId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
    }

    // GET - user get
    @GetMapping("/")
    public ResponseEntity<List<UserProfile>> getAllUserProfiles() {
        return ResponseEntity.ok(this.userProfileService.getAllUserProfiles());
    }

    // GET - user get
    @GetMapping("/{userProfileId}")
    public ResponseEntity<UserProfileDto> getSingleUserProfile(@PathVariable Integer userProfileId) {
        return ResponseEntity.ok(this.userProfileService.getUserProfileById(userProfileId));
    }

}