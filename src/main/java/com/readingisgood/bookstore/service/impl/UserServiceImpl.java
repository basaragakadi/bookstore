package com.readingisgood.bookstore.service.impl;

import org.springframework.stereotype.Service;

import com.readingisgood.bookstore.entity.UserEntity;
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
	public UserEntity saveUser(UserEntity user) {
		return userRepository.saveAndFlush(user);
	}
	
	@Override
	public UserEntity getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public boolean userExists(String username) {
		return userRepository.existsByUsername(username);
	}

}
