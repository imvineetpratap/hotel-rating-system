package com.lcwd.rating.repositories;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lcwd.rating.entities.Rating;
@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
	public List<Rating>findByUserId(String userId);
	public List<Rating> findByHotelId(String hotelId);
}
