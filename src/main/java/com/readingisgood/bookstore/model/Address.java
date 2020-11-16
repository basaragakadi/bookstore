/**
 * 
 */
package com.readingisgood.bookstore.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author basaragakadi
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@NotBlank(message = "country must not be blank")
	private String country;
	
	@NotBlank(message = "city must not be blank")
	private String city;
	
	@NotBlank(message = "details must not be blank")
	private String details;
	
}
