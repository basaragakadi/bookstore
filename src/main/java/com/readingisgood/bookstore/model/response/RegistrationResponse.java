package com.readingisgood.bookstore.model.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author basaragakadi
 *
 */
@Data
@Builder
public class RegistrationResponse {
	
	private final String username;
	private final String message;
	
}
