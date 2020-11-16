package com.readingisgood.bookstore.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.readingisgood.bookstore.configuration.CustomUserDetailsService;
import com.readingisgood.bookstore.model.request.AuthenticationRequest;
import com.readingisgood.bookstore.model.response.AuthenticationResponse;
import com.readingisgood.bookstore.service.AuthenticationService;
import com.readingisgood.bookstore.util.JwtUtil;

import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 *
 */
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final CustomUserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws Exception {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return AuthenticationResponse
				.builder()
				.jwt(jwt)
				.build();

	}

}
