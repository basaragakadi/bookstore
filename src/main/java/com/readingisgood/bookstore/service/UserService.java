package com.readingisgood.bookstore.service;

import com.readingisgood.bookstore.entity.UserEntity;
import com.readingisgood.bookstore.exception.UserNotFoundException;

/**
 * @author basaragakadi
 *
 */
public interface UserService {
	
	public UserEntity saveUser(UserEntity user);
	
	public UserEntity getUserByUsername(String username) throws UserNotFoundException;
	
	public boolean userExists(String username);
	
}
