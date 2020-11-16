/**
 * 
 */
package com.readingisgood.bookstore.exception;

/**
 * @author basaragakadi
 *
 */
public class NoOrderFoundException extends Exception {

	private static final long serialVersionUID = -7640745186257528701L;

	public NoOrderFoundException(String errorMessage) {
		super(errorMessage);
	}
	
}
