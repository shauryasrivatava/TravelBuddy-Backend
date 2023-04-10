package com.travel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.dao.UserProfileDao;
import com.travel.model.UserProfile;
import com.travel.payloads.UserProfileDto;

@Service
public class JwtUserProfileService implements UserDetailsService {
	
	@Autowired
	private UserProfileDao userProfileDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserProfile user = userProfileDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public UserProfile CreateUserProfile(UserProfileDto user) {
		UserProfile newUser = new UserProfile();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setAboutMe(user.getAboutMe());
		newUser.setContact(user.getContact());
		newUser.setGender(user.getGender());
		newUser.setLocation(user.getLocation());
		newUser.setTotalExpenses(user.getTotalExpenses());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userProfileDao.save(newUser);
	}
	


}