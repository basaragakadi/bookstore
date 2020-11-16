/**
 * 
 */
package com.readingisgood.bookstore.exception;

/**
 * @author basaragakadi
 *
 * Exception class for errors faced when inserting a new user entity
 *
 */
public class UserNotInsertedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6773000686850294978L;

	public UserNotInsertedException(String errorMessage) {
		super(errorMessage);
	}
	
}
