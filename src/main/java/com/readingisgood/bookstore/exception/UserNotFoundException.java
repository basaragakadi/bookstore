/**
 * 
 */
package com.readingisgood.bookstore.exception;

/**
 * @author basaragakadi
 *
 */
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 5332552987203777817L;

	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
}
