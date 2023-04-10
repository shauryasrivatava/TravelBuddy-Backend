package com.travel.service;

import java.util.List;

import com.travel.model.UserProfile;
import com.travel.payloads.UserProfileDto;


public interface UserProfileService {

//	UserProfileDto createUserProfile (UserProfileDto user);
    UserProfileDto updateUserProfile (UserProfileDto user, Integer userProfileId);
    UserProfileDto getUserProfileById(Integer userProfileId);
    List<UserProfile> getAllUserProfiles();
    void deleteUserProfile(Integer userProfileId);
}
