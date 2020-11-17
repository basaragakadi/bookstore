package com.readingisgood.bookstore.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.bookstore.exception.UserAlreadyExistsException;
import com.readingisgood.bookstore.exception.UserNotInsertedException;
import com.readingisgood.bookstore.model.request.RegistrationRequest;
import com.readingisgood.bookstore.model.response.RegistrationResponse;
import com.readingisgood.bookstore.service.RegistrationService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 * 
 * Controller class for handling user endpoints
 * 
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final RegistrationService registrationService;
	
	@ApiOperation("Registers a new user")
	@PostMapping("/register")
	public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws UserAlreadyExistsException, UserNotInsertedException {
		return ResponseEntity.ok(registrationService.register(registrationRequest));
	}
	
}
