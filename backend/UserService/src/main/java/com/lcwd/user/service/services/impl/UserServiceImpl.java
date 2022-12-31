package com.lcwd.user.service.services.impl;
import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

//using rest template for fetcing ratings of hotel

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
private HotelService hotelService;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        // generating unique userid
        String randomUserID = UUID.randomUUID().toString();
        user.setUserId(randomUserID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        // TODO Auto-generated method stub
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with given id was not found on server!! :" + userId));
        //fetch ratings of the above user from rating service
        // http://192.168.1.4:8083/ratings/users/08ff6c1c-baab-408e-8a72-3c817a575ab7
      Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{}", ratingsOfUser);
List<Rating> ratings= Arrays.stream(ratingsOfUser).toList();

List<Rating> ratingList=  ratings.stream().map(rating ->{
            //api call to hotel service to get the hotel
//          http://localhost:8082/hotels/3b19334d-a249-4092-999f-1134312f4d48
//          ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://MY-HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//         Hotel hotel= forEntity.getBody(); yeh pehle resttemplate use karke nikal rahe the ab feign se nikalenge
    Hotel hotel= hotelService.getHotel(rating.getHotelId());
//         logger.info("response status code",forEntity.getStatusCode());
          //set the hotel to rating
    rating.setHotel(hotel);
    //return the rating
            return rating;
        } ).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with given id was not found on server!! :" + userId));
        if (user != null) {
            this.userRepository.deleteById(userId);
        }
    }

    @Override
    public User updateUser(User user) {
        // TODO Auto-generated method stub
//		System.out.println( user.getUserId());
        String id = user.getUserId();
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user with given id " + id + " was not found on server!! Unable to update"));
        return userRepository.save(user);

        //	if(id!=null) {
//		return userRepository.save(user);
//		}
    }

}
