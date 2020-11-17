/**
 * 
 */
package com.readingisgood.bookstore.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readingisgood.bookstore.BookstoreApplication;
import com.readingisgood.bookstore.exception.UserAlreadyExistsException;
import com.readingisgood.bookstore.exception.UserNotInsertedException;
import com.readingisgood.bookstore.model.Address;
import com.readingisgood.bookstore.model.request.RegistrationRequest;
import com.readingisgood.bookstore.model.response.RegistrationResponse;
import com.readingisgood.bookstore.service.RegistrationService;

/**
 * @author basaragakadi
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BookstoreApplication.class })
@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private RegistrationService registrationService;
	
	@Test
	void registrationIsNotSuccessful_whenUserAlreadyExists_thenReturnStatusConflict() throws Exception {

		RegistrationRequest registrationRequest = getValidRegistrationRequest();

		when(registrationService.register(any())).thenThrow(new UserAlreadyExistsException("User already exists"));

		this.mockMvc.perform(
				post("/user/register")
				.content(objectMapper.writeValueAsString(registrationRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isConflict());

	}
	
	@Test
	void registrationIsNotSuccessful_whenUserIsNotInserted_thenReturnStatusInternalServerError() throws Exception {

		RegistrationRequest registrationRequest = getValidRegistrationRequest();

		when(registrationService.register(any())).thenThrow(new UserNotInsertedException("User is not inserted"));

		this.mockMvc.perform(
				post("/user/register")
				.content(objectMapper.writeValueAsString(registrationRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isInternalServerError());

	}

	@Test
	void registrationIsSuccessful_whenRequestIsValid_thenReturnStatusOk() throws Exception{

		RegistrationRequest registrationRequest = getValidRegistrationRequest();

		when(registrationService.register(any())).thenReturn(RegistrationResponse.builder().username("username").message("message").build());

		this.mockMvc.perform(
				post("/user/register")
				.content(objectMapper.writeValueAsString(registrationRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.username").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());

	}

	@Test
	void registrationIsNotSuccessful_whenRequestIsNotValid_thenReturnStatusBadRequest() throws Exception{

		RegistrationRequest registrationRequest = getInValidRegistrationRequest();

		this.mockMvc.perform(
				post("/user/register")
				.content(objectMapper.writeValueAsString(registrationRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

	}

	@Test
	void registrationIsNotSuccessful_whenRequestIsNotReadable_thenReturnStatusBadRequest() throws Exception{

		String unreadableRequest = "";

		this.mockMvc.perform(
				post("/user/register")
				.content(unreadableRequest)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

		unreadableRequest = ",";

		this.mockMvc.perform(
				post("/user/register")
				.content(unreadableRequest)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

	}

	private RegistrationRequest getValidRegistrationRequest() {

		Address address = Address.builder()
				.country("Turkey")
				.city("Izmir")
				.details("Konak")
				.build();

		return RegistrationRequest.builder()
				.address(address)
				.email("test@test.com")
				.name("user")
				.password("password")
				.phone("0000000000")
				.surname("user")
				.username("username")
				.build();

	}

	private RegistrationRequest getInValidRegistrationRequest() {

		Address address = Address.builder()
				.country("Turkey")
				.city("Izmir")
				.details("Konak")
				.build();

		// Blank username
		return RegistrationRequest.builder()
				.address(address)
				.email("test@test.com")
				.name("user")
				.password("password")
				.phone("0000000000")
				.surname("user")
				.username("")
				.build();

	}

}
