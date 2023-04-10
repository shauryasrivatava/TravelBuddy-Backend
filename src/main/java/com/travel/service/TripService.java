package com.travel.service;

import com.travel.payloads.TripDto;

import java.util.List;

public interface TripService {
    public TripDto createTrip(TripDto tripDto, Integer userProfileId);
    public TripDto updateTrip(TripDto tripDto, Integer tripId);

    public void deleteTrip(Integer tripId);

    List<TripDto> getAllTrip();

    public TripDto getTripById(Integer tripId);
    
    public List<TripDto> getTripByUserProfile(Integer userProfileId);
}
