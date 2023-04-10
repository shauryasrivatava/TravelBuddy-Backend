package com.travel.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dao.UserProfileDao;
import com.travel.exceptions.ResourceNotFoundException;
import com.travel.model.UserProfile;
import com.travel.payloads.UserProfileDto;
import com.travel.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	   @Autowired
	    private UserProfileDao userProfileDao;

	    @Autowired
	    private ModelMapper modelMapper;

		@Override
		public UserProfileDto updateUserProfile(UserProfileDto userProfileDto, Integer userProfileId) {
	        UserProfile userProfile = this.userProfileDao.findById(userProfileId)
	                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userProfileId));

	        userProfile.setUsername(userProfileDto.getUsername());
	        userProfile.setFirstName(userProfileDto.getFirstName());
	        userProfile.setLastName(userProfileDto.getLastName());
	        userProfile.setAboutMe(userProfileDto.getAboutMe());
	        userProfile.setContact(userProfileDto.getContact());
	        userProfile.setGender(userProfileDto.getGender());
	        userProfile.setLocation(userProfileDto.getLocation());
	        userProfile.setTotalExpenses(userProfileDto.getTotalExpenses());
	       
	        UserProfile updatedUserProfile = this.userProfileDao.save(userProfile);
	        UserProfileDto userProfileDto1 = this.modelMapper.map(updatedUserProfile,UserProfileDto.class);
	        return userProfileDto1;
		}

		@Override
		public UserProfileDto getUserProfileById(Integer userProfileId) {
			 UserProfile userProfile = this.userProfileDao.findById(userProfileId)
		                .orElseThrow(() -> new ResourceNotFoundException("UserProfile", " Id ", userProfileId));

		        return this.modelMapper.map(userProfile, UserProfileDto.class);
		}

		@Override
		public List<UserProfile> getAllUserProfiles() {
			List<UserProfile> userProfiles = (List<UserProfile>) this.userProfileDao.findAll();
//	        List<UserProfileDTO> userProfileDTOS = userProfiles.stream().map(userProfile -> this.userProfileToDto(userProfile)).collect(Collectors.toList());
	        return userProfiles;
		}
		


		@Override
		public void deleteUserProfile(Integer userProfileId) {
			UserProfile userProfile = this.userProfileDao.findById(userProfileId)
	                .orElseThrow(() -> new ResourceNotFoundException("UserProfile", "Id", userProfileId));
	        this.userProfileDao.delete(userProfile);
			
		}
	    
	    public UserProfile dtoToUserProfile(UserProfileDto userProfileDTO) {
	        UserProfile userProfile = this.modelMapper.map(userProfileDTO, UserProfile.class);
	        return userProfile;
	    }

	    public UserProfileDto userProfileToDto(UserProfile userProfile) {
	        UserProfileDto userProfileDTO = this.modelMapper.map(userProfile, UserProfileDto.class);
	        return userProfileDTO;
	    }
	    

}
