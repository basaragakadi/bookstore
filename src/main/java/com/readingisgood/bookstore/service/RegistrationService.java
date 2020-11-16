package com.readingisgood.bookstore.service;

import javax.validation.Valid;

import com.readingisgood.bookstore.model.request.RegistrationRequest;
import com.readingisgood.bookstore.model.response.RegistrationResponse;

/**
 * @author basaragakadi
 *
 */
public interface RegistrationService {

	public RegistrationResponse register(@Valid RegistrationRequest registrationRequest) throws Exception;

}
