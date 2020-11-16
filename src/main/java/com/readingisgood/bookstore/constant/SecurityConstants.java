/**
 * 
 */
package com.readingisgood.bookstore.constant;

/**
 * @author basaragakadi
 *
 */
public final class SecurityConstants {

	private SecurityConstants() {
		
	}
	
	public static final long EXPIRATION_TIME_IN_MILLIS = 3600000;
	
	public static final String BEARER_TOKEN_PREFIX = "Bearer ";
	public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	
}