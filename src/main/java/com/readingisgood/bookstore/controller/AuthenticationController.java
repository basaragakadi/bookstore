package com.readingisgood.bookstore.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.bookstore.model.request.AuthenticationRequest;
import com.readingisgood.bookstore.model.response.AuthenticationResponse;
import com.readingisgood.bookstore.service.AuthenticationService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 * 
 * Controller class for authentication
 * 
 */
@RestController
@AllArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;
	
	@ApiOperation(value = "Authenticate user with username and password")
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
		return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
	}
	
}
