package com.lcwd.user.service.controllers;

import java.util.List;

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
	//create
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user){
//		System.out.println(user);
		User us=userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(us);
	}
	//single user get
	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
	User user=	userService.getUser(userId);
	return ResponseEntity.ok(user);
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
