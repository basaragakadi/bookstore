package com.readingisgood.bookstore.model.error;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author basaragakadi
 *
 * Class for representing error responses
 *
 */
@Data
@Builder
@AllArgsConstructor
public class ErrorResponseBody {

	private final int statusCode;
	private final Date timestamp;
	private final String message;
	private final String description;
	
}