package com.readingisgood.bookstore.model.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author basaragakadi
 *
 * Class for representing registration response
 *
 */
@Data
@Builder
public class RegistrationResponse {
	
	private final String username;
	private final String message;
	
}
