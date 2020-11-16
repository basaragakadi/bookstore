/**
 * 
 */
package com.readingisgood.bookstore.exception;

/**
 * @author basaragakadi
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
