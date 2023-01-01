package com.lcwd.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired
	private RatingService ratingService;

	// create
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}
	
	//get all
	@GetMapping
	public  ResponseEntity<List<Rating>> getAllRatings(){
		return  ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatings());
	}
	//get ratings of user by user id
	@GetMapping("/users/{userId}")
	public  ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable String userId){
		return  ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserId(userId));
	}
	//get ratings of hotels by hotel id
	@GetMapping("/hotels/{hotelId}")
	public  ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable String hotelId){
		return  ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotelId(hotelId));
	}

	// update
	@PutMapping("/update")
	public ResponseEntity<Rating> update(@RequestBody Rating rating) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.update(rating));
	}


	@GetMapping("/delete/{ratingId}")
	public  void DeletebyId(@PathVariable String ratingId){
		ratingService.delete(ratingId);
	}
}
