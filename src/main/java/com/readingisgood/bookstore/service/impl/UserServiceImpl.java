package com.readingisgood.bookstore.service.impl;

import org.springframework.stereotype.Service;

import com.readingisgood.bookstore.entity.UserEntity;
import com.readingisgood.bookstore.exception.UserNotFoundException;
import com.readingisgood.bookstore.exception.UserNotInsertedException;
import com.readingisgood.bookstore.repository.UserRepository;
import com.readingisgood.bookstore.service.UserService;

import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 *
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Override
	public UserEntity saveUser(UserEntity user) throws UserNotInsertedException{
		UserEntity userEntity = userRepository.saveAndFlush(user);
		
		if(userEntity == null)
			throw new UserNotInsertedException("Could not insert user from db");
		
		return userEntity;
		
	}
	
	@Override
	public UserEntity getUserByUsername(String username) throws UserNotFoundException{
		UserEntity userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null)
			throw new UserNotFoundException("User not found with username: " + username);
		
		return userEntity;
		
	}
	
	@Override
	public boolean userExists(String username) {
		return userRepository.existsByUsername(username);
	}

}
