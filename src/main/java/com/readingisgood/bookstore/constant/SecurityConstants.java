/**
 * 
 */
package com.readingisgood.bookstore.constant;

/**
 * @author basaragakadi
 *
 * Constant class for holding security constant values which are used for security configuration.
 *
 */
public final class SecurityConstants {

	private SecurityConstants() {
		
	}
	
	public static final long EXPIRATION_TIME_IN_MILLIS = 3600000;
	
	public static final String BEARER_TOKEN_PREFIX = "Bearer ";
	public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	
}