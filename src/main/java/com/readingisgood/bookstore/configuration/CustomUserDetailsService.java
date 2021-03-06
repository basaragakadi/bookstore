package com.readingisgood.bookstore.configuration;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.readingisgood.bookstore.entity.UserEntity;
import com.readingisgood.bookstore.exception.UserNotFoundException;
import com.readingisgood.bookstore.service.UserService;

import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 *
 * Class for configuring UserDetailsService.
 * loadByUsername method is overridden to authenticate users from database.
 *
 */
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity;
		
		try {
			userEntity = userService.getUserByUsername(username);
		} catch (UserNotFoundException e) {
			throw new UsernameNotFoundException("username: " + username + " not found");
		}	
		
		return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
		
	}

}
