package com.travel.dao;

import com.travel.model.Trip;
import com.travel.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripDao extends JpaRepository<Trip, Integer> {
    List<Trip> findByUser(UserProfile userProfile);
}
