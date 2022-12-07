package com.lcwd.user.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
@Service
public class UserServiceImpl implements UserService {
@Autowired
	private UserRepository userRepository;
	@Override
	public User saveUser(User user) {
		// generating unique userid
	String randomUserID=UUID.randomUUID().toString();
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
	User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id was not found on server!! :"+userId));
	return user;
	}

	@Override
	public void deleteUser(String userId) {
		User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id was not found on server!! :"+userId));
		if(user!=null) 
		{
		this.userRepository.deleteById(userId);
		}
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
//		System.out.println( user.getUserId());
		String id=user.getUserId();
		userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user with given id "+id +" was not found on server!! Unable to update"));
		return userRepository.save(user);
		
		//	if(id!=null) { 
//		return userRepository.save(user);
//		}
	}

}
