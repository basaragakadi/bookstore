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
import com.readingisgood.bookstore.model.request.AuthenticationRequest;
import com.readingisgood.bookstore.model.response.AuthenticationResponse;
import com.readingisgood.bookstore.service.AuthenticationService;

/**
 * @author basaragakadi
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BookstoreApplication.class })
@AutoConfigureMockMvc
@SpringBootTest
class AuthenticationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private AuthenticationService authenticationService;

	@Test
	void authenticationIsSuccessful_whenAuthenticationRequestIsValid_thenStatusOk() throws Exception{
		
		AuthenticationRequest authenticationRequest = getValidAuthenticationRequest();
		
		when(authenticationService.authenticate(any())).thenReturn(AuthenticationResponse.builder().jwt("jwt").build());
		
		this.mockMvc.perform(
				post("/authenticate")
				.content(objectMapper.writeValueAsString(authenticationRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.jwt").exists());
		
	}
	
	@Test
	void authenticationFails_whenAuthenticationRequestIsInValid_thenStatusBadRequest() throws Exception{
		
		AuthenticationRequest authenticationRequest = getInValidAuthenticationRequest();
		
		when(authenticationService.authenticate(any())).thenReturn(AuthenticationResponse.builder().jwt("jwt").build());
		
		this.mockMvc.perform(
				post("/authenticate")
				.content(objectMapper.writeValueAsString(authenticationRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
		
	}

	private AuthenticationRequest getInValidAuthenticationRequest() {
		
		// Missing username
		return AuthenticationRequest.builder()
				.username("")
				.password("password")
				.build();
		
	}

	private AuthenticationRequest getValidAuthenticationRequest() {
		
		return AuthenticationRequest.builder()
				.username("username")
				.password("password")
				.build();
		
	}

}
