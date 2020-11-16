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

	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;
	
}