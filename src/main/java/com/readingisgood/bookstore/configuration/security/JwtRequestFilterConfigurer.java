package com.readingisgood.bookstore.configuration.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.readingisgood.bookstore.configuration.CustomUserDetailsService;
import com.readingisgood.bookstore.constant.SecurityConstants;
import com.readingisgood.bookstore.util.JwtUtil;

import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 * 
 * Class for Jwt request filtering configuration.
 * OncePerRequestFilter and is chosen to require authentication for every request.
 * 
 * TODO catch exceptions thrown from this filter implementation and return response entity with ErrorResponseBody
 */
@Component
@AllArgsConstructor
public class JwtRequestFilterConfigurer extends OncePerRequestFilter{

	private final CustomUserDetailsService userDetailService;
	private final JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER_KEY);
		
		String username = null;
		String jwt = null;
		
		if(authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.BEARER_TOKEN_PREFIX)) {
			jwt = jwtUtil.getTokenFromAuthorizationHeader(authorizationHeader);
			username = jwtUtil.extractUsername(jwt);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
			if(jwtUtil.validateToken(jwt, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
		}
		filterChain.doFilter(request, response);
		
	}

	
	
}
