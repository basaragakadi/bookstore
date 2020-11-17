package com.readingisgood.bookstore.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.readingisgood.bookstore.entity.AddressEntity;
import com.readingisgood.bookstore.entity.UserEntity;
import com.readingisgood.bookstore.exception.UserAlreadyExistsException;
import com.readingisgood.bookstore.exception.UserNotInsertedException;
import com.readingisgood.bookstore.model.Address;
import com.readingisgood.bookstore.model.request.RegistrationRequest;
import com.readingisgood.bookstore.model.response.RegistrationResponse;
import com.readingisgood.bookstore.service.AddressService;
import com.readingisgood.bookstore.service.RegistrationService;
import com.readingisgood.bookstore.service.UserService;

import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 *
 */
@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final UserService userService;
	private final AddressService addressService;
	
	@Transactional
	@Override
	public RegistrationResponse register(RegistrationRequest registrationRequest) throws UserAlreadyExistsException, UserNotInsertedException {
		if(userService.userExists(registrationRequest.getUsername()))
			throw new UserAlreadyExistsException("Could not register user with username: " + registrationRequest.getUsername());
		
		UserEntity userEntity = UserEntity.builder()
				.username(registrationRequest.getUsername())
				.password(registrationRequest.getPassword())
				.email(registrationRequest.getEmail())
				.name(registrationRequest.getName())
				.surname(registrationRequest.getSurname())
				.phone(registrationRequest.getPhone())
				.build();
		
		userEntity = userService.saveUser(userEntity);

		Address address = registrationRequest.getAddress();
		AddressEntity addressEntity = AddressEntity.builder()
				.country(address.getCountry())
				.city(address.getCity())
				.details(address.getDetails())
				.userId(userEntity.getId())
				.build();
		
		addressService.saveAddress(addressEntity);
		
		return RegistrationResponse.builder()
				.username(registrationRequest.getUsername())
				.message("Registered user successfully")
				.build();
	}

}
