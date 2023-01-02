package com.lcwd.rating.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repositories.RatingRepository;
import com.lcwd.rating.service.RatingService;
@Service
public class RatingServiceImpl implements RatingService{
@Autowired
	private RatingRepository ratingrepo;
	@Override
	public Rating create(Rating rating) {
		// TODO Auto-generated method stub
//		String randomRatingID=UUID.randomUUID().toString();
//	      rating.setRatingId(randomRatingID);
		return ratingrepo.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		// TODO Auto-generated method stub
		return ratingrepo.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		// TODO Auto-generated method stub
		return ratingrepo.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return ratingrepo.findByHotelId(hotelId);
	}

	@Override
	public Rating update(Rating rating) {
		if (ratingrepo.findById(rating.getRatingId()).isPresent()) {
			 ratingrepo.save(rating);
			return rating;
		}
		else {
			return null;
		}
	}

	@Override
	public void delete(String ratingId) {
		this.ratingrepo.deleteById(ratingId);
	}


}
