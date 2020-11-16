/**
 * 
 */
package com.readingisgood.bookstore.exception;

/**
 * @author basaragakadi
 *
 */
public class OrderNotInsertedException extends Exception {

	private static final long serialVersionUID = -4103431371007409928L;
	
	public OrderNotInsertedException(String errorMessage) {
		super(errorMessage);
	}

}
