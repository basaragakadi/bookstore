/**
 * 
 */
package com.readingisgood.bookstore.exception;

/**
 * @author basaragakadi
 *
 * Exception class for handling situations when no stock information found for a specific product
 *
 */
public class StockNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1687155037460249506L;

	public StockNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
}
