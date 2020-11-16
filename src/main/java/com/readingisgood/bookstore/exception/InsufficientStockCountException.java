/**
 * 
 */
package com.readingisgood.bookstore.exception;

/**
 * @author basaragakadi
 * 
 */
public class InsufficientStockCountException extends Exception {
	
	private static final long serialVersionUID = 2916385304994788040L;

	public InsufficientStockCountException(String errorMessage) {
		super(errorMessage);
	}
	
}
