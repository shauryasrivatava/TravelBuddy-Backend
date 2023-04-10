package com.travel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.payloads.ApiResponse;
import com.travel.payloads.TripDto;
import com.travel.service.FileService;
import com.travel.service.TripService;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/")
public class TripController {

    private Logger logger= LoggerFactory.getLogger(TripController.class);

    @Autowired
    private TripService tripService;
    
    @Autowired
    private FileService fileService;

    @Autowired
    private ObjectMapper mapper;
    
    @Value("${project.image}")
    private String path1;
    
    @Value("${project.invoice}")
    private String path2;
    

    @PostMapping("/userProfiles/{userProfileId}/trip")
    public ResponseEntity<TripDto> addTripInformation(@Valid
            @RequestParam("image") MultipartFile image,
            @RequestParam("invoice")MultipartFile invoice,
            @RequestParam("tripData") String tripData,
            @PathVariable Integer userProfileId) throws IOException
    {
        this.logger.info("add user request");
        logger.info("image information {}", image.getOriginalFilename());
        logger.info("invoice information {}", invoice.getOriginalFilename());
        logger.info("trip:{}",tripData);


        TripDto tripDto=null;

        try {
//				converting string into j son
            tripDto= mapper.readValue(tripData, TripDto.class);
            TripDto createTrip= this.tripService.createTrip(tripDto, userProfileId);
            tripDto= createTrip;
    
            String fileName1 = fileService.uploadImage(path1, image);
            String fileName2 = fileService.uploadImage(path2, invoice);
            
            tripDto.setImageName(fileName1);
            tripDto.setInvoiceName(fileName2);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            return new ResponseEntity<TripDto>(tripDto,HttpStatus.BAD_REQUEST);
        }

        

        
        
        this.logger.info("trip data is :{}",tripDto);

        return new ResponseEntity<TripDto>(tripDto,HttpStatus.CREATED);

    }
    @GetMapping("/userProfiles/{userProfiled}/trip")
    public ResponseEntity<List<TripDto>> getTripByUserProfile(
    		@PathVariable Integer userProfileId
    		){
    	List<TripDto> trips = tripService.getTripByUserProfile(userProfileId);
    	
    	return new ResponseEntity<List<TripDto>>(trips,HttpStatus.OK);	
    }
    
    @GetMapping("/trips")
    public ResponseEntity<List<TripDto>> getAllTrips(){
    	
    	List<TripDto> trips= tripService.getAllTrip();
    	
    	return new ResponseEntity<List<TripDto>>(trips,HttpStatus.OK); 
    	}
    
    @GetMapping("/trips/{tripId}")
    public ResponseEntity<TripDto> getTripById(@PathVariable Integer tripId){
    	TripDto tripDto = tripService.getTripById(tripId);
    	
    	return new ResponseEntity<TripDto>(tripDto,HttpStatus.OK);
    }
    @DeleteMapping("/trips/{tripId}")
    public ApiResponse deleteTrip(@PathVariable Integer tripId){
    	tripService.deleteTrip(tripId);
    	return new ApiResponse("Trip isSuccessfully Deleted !!",true);
    }
    
    @PutMapping("/trips/{tripId}")
	public ResponseEntity<TripDto> updateTrip(@Valid @RequestBody TripDto tripDto, @PathVariable Integer tripId){
		TripDto updatedTrip= this.tripService.updateTrip(tripDto, tripId);
		return new ResponseEntity<TripDto>(updatedTrip,HttpStatus.OK);
	}
}
