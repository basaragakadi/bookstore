package com.readingisgood.bookstore.exception;

/**
 * @author basaragakadi
 *
 */
public class UserAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 5332552987203777817L;
	
	public UserAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
	
}
