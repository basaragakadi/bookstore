package com.readingisgood.bookstore.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.bookstore.model.request.RegistrationRequest;
import com.readingisgood.bookstore.service.RegistrationService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 *
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

	private RegistrationService registrationService;
	
	@ApiOperation("Registers a new user")
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws Exception {
		return ResponseEntity.ok(registrationService.register(registrationRequest));
	}
	
}
