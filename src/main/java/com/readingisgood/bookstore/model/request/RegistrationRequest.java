package com.readingisgood.bookstore.model.request;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.readingisgood.bookstore.model.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author basaragakadi
 *
 * Class for representing registration requests
 *
 */
@Data
@Builder
@AllArgsConstructor
public class RegistrationRequest {

	@NotBlank(message = "Username must not be blank")
	@Size(min = 6, max = 50, message = "Username size must be between 6 and 50 characters")
	private final String username;
	
	@NotBlank(message = "Password must not be blank")
	@Size(min = 6, max = 50, message = "Password size must be between 6 and 50 characters")
	private final String password;
	
	@NotBlank(message = "Password must not be blank")
	@Email(message = "E-mail must be valid")
	private final String email;
	
	@NotBlank(message = "Name must not be blank")
	private final String name;
	
	@NotBlank(message = "Surname must not be blank")
	private final String surname;
	
	@NotBlank(message = "Phone must not be blank")
	private final String phone;
	
	@Valid
	@NotNull(message = "Address must not be null")
	private final Address address;
	
}
