package com.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.model.UserProfile;

@Repository
public interface UserProfileDao extends JpaRepository<UserProfile, Integer> {
	
	UserProfile findByUsername(String username);
	
}