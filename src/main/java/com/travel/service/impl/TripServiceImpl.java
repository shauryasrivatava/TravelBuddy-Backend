package com.travel.service.impl;

import com.travel.dao.TripDao;
import com.travel.dao.UserProfileDao;
import com.travel.exceptions.ResourceNotFoundException;
import com.travel.model.Trip;
import com.travel.model.UserProfile;
import com.travel.payloads.TripDto;
import com.travel.service.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private TripDao tripDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserProfileDao userProfileDao;
    


    @Override
    public TripDto createTrip(TripDto tripDto, Integer userProfileId) {

        UserProfile userProfile = this.userProfileDao.findById(userProfileId)
                .orElseThrow(()-> new ResourceNotFoundException("UserProfile","User id",userProfileId));

        Trip trip= this.modelMapper.map(tripDto, Trip.class);

        trip.setUser(userProfile);

        Trip newTrip=this.tripDao.save(trip);
        return this.modelMapper.map(newTrip, TripDto.class);
    }

    @Override
    public TripDto updateTrip(TripDto tripDto, Integer tripId) {
        Trip trip= tripDao.findById(tripId)
        		.orElseThrow(()-> new ResourceNotFoundException("Trip","trip id",tripId));
        
        trip.setContent(tripDto.getContent());
        trip.setImageName(tripDto.getImageName());
        trip.setInvoiceName(tripDto.getInvoiceName());
        trip.setLocation(tripDto.getLocation());
        trip.setTitle(tripDto.getTitle());
        trip.setTripExpense(trip.getTripExpense());
        
        Trip updatedTrip = tripDao.save(trip);
		
		return this.modelMapper.map(updatedTrip, TripDto.class);
    }

    @Override
    public void deleteTrip(Integer tripId) {
    	
    	Trip trip= tripDao.findById(tripId)
    			.orElseThrow(()-> new ResourceNotFoundException("Trip","trip id",tripId));
    	tripDao.delete(trip);

    }

    @Override
    public List<TripDto> getAllTrip() {
        
    	List<Trip> allTrip = tripDao.findAll();
    	List<TripDto> tripDtos= allTrip.stream().map((trip)-> modelMapper.map(trip, TripDto.class))
    			.collect(Collectors.toList());
    	
    	
    	return tripDtos;
    }

    @Override
    public TripDto getTripById(Integer tripId) {
        
    	Trip trip = tripDao.findById(tripId)
    			.orElseThrow(()-> new ResourceNotFoundException("Trip","trip id",tripId));
    	
    	return modelMapper.map(trip, TripDto.class);
    }

	@Override
	public List<TripDto> getTripByUserProfile(Integer userProfileId) {
		
		UserProfile userProfile= userProfileDao.findById(userProfileId)
				.orElseThrow(()-> new ResourceNotFoundException("Trip","trip id",userProfileId));
		List<Trip> trips= tripDao.findByUser(userProfile);
		
		List<TripDto> tripDtos = trips.stream().map((trip)-> modelMapper.map(trip, TripDto.class)).collect(Collectors.toList());
		
		
		return tripDtos;
	}
}
