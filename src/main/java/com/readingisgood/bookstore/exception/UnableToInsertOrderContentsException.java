/**
 * 
 */
package com.readingisgood.bookstore.exception;

/**
 * @author basaragakadi
 *
 */
public class UnableToInsertOrderContentsException extends Exception {

	private static final long serialVersionUID = 7370182047453024694L;

	public UnableToInsertOrderContentsException(String errorMessage) {
		super(errorMessage);
	}
	
}
