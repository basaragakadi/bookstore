package com.readingisgood.bookstore.model.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author basaragakadi
 *
 * Class for representing authentication response
 *
 */
@Data
@Builder
public class AuthenticationResponse {

	private final String jwt;
	
}
