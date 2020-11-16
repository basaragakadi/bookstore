package com.readingisgood.bookstore.service;

import com.readingisgood.bookstore.entity.UserEntity;

/**
 * @author basaragakadi
 *
 */
public interface UserService {
	
	public UserEntity saveUser(UserEntity user);
	
	public UserEntity getUserByUsername(String username);
	
	public boolean userExists(String username);
	
}
