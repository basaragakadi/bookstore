package com.readingisgood.bookstore.exception;

/**
 * @author basaragakadi
 *
 * Exception class for preventing duplicate user registration
 *
 */
public class UserAlreadyExistsException extends Exception {

	/**
	 *  Auto generated serial version id
	 */
	private static final long serialVersionUID = 5332552987203777817L;
	
	public UserAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
	
}
