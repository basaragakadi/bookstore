/**
 * 
 */
package com.readingisgood.bookstore.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author basaragakadi
 *
 * Class for representing address model in requests
 *
 */
@Data
@Builder
@AllArgsConstructor
public class Address {

	@NotBlank(message = "country must not be blank")
	private final String country;
	
	@NotBlank(message = "city must not be blank")
	private final String city;
	
	@NotBlank(message = "details must not be blank")
	private final String details;
	
}
