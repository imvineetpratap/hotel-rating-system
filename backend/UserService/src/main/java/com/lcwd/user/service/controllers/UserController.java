package com.lcwd.user.service.controllers;

import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	//create
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user){
//		System.out.println(user);
		User us=userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(us);
	}
	//single user get
	int retrycount=1;
	@GetMapping("/{userId}")
//	@CircuitBreaker(name="Rating_Hotel_Breaker",fallbackMethod = "ratingHotelFallback")
//	@Retry(name="Rating_Hotel_retry",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name="userRateLimiter", fallbackMethod="ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		logger.info("Get Single User Handler: UserController");
		logger.info("Rery count:{}",retrycount);
		retrycount++;
		User user=	userService.getUser(userId);
	return ResponseEntity.ok(user);
	}
	//fallback method for circuitbreaker
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
		logger.info("Fallback is executed because service is down : ", ex.getMessage());
		User user = User.builder()
				.email("dummy@gmail.com")
				.name("Dummy")
				.about("This user is created dummy because some service is down")
				.userId("141234")
				.build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}







	//all
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUsers=userService.getAllUser();
		return ResponseEntity.ok(allUsers);
	}
	@DeleteMapping("/{userId}")
	public void DeleteUser(@PathVariable String userId){
	this.userService.deleteUser(userId);
	}
	
	@PostMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User user){
//		System.out.println(user);
		User us=userService.updateUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(us);
	}

}
