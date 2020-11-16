/**
 * 
 */
package com.readingisgood.bookstore.exception;

/**
 * @author basaragakadi
 *
 * Exception class for handling situations when no order is found for that user
 *
 */
public class OrderNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6003068293601734437L;

	public OrderNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
}
