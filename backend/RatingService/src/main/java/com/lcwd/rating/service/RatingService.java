package com.lcwd.rating.service;

import java.util.List;

import com.lcwd.rating.entities.Rating;
public interface RatingService {
	
	//create
	Rating create(Rating rating);
	//get all ratings
	List<Rating> getRatings();
	//get all ratings by user
	List<Rating> getRatingByUserId(String userId);
	//get all by hotel
	List<Rating>getRatingByHotelId(String hotelId);
	//update
	Rating update(Rating rating);
	//delete
	void delete(String ratingId);
}
