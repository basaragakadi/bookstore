package com.readingisgood.bookstore.service;

import com.readingisgood.bookstore.model.request.AuthenticationRequest;
import com.readingisgood.bookstore.model.response.AuthenticationResponse;

/**
 * @author basaragakadi
 *
 */
public interface AuthenticationService {
	
	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

}
