package com.readingisgood.bookstore.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author basaragakadi
 *
 * Class for representing authentication requests
 *
 */
@Data
@Builder
@RequiredArgsConstructor
public class AuthenticationRequest {

	@NotBlank(message = "Username must not be blank")
	@Size(min = 6, max = 50, message = "Username length must be between 6 and 50 characters")
	private final String username;
	
	@NotBlank(message = "Password must not be blank")
	@Size(min = 6, max = 50, message = "Password length must be between 6 and 50 characters")
	private final String password;
	
}
