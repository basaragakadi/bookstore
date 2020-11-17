package com.readingisgood.bookstore.service;

import com.readingisgood.bookstore.entity.UserEntity;
import com.readingisgood.bookstore.exception.UserNotFoundException;
import com.readingisgood.bookstore.exception.UserNotInsertedException;

/**
 * @author basaragakadi
 *
 */
public interface UserService {
	
	public UserEntity saveUser(UserEntity user) throws UserNotInsertedException;
	
	public UserEntity getUserByUsername(String username) throws UserNotFoundException;
	
	public boolean userExists(String username);
	
}
