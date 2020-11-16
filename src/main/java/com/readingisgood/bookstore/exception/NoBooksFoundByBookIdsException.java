/**
 * 
 */
package com.readingisgood.bookstore.exception;

/**
 * @author basaragakadi
 *
 */
public class NoBooksFoundByBookIdsException extends Exception {

	private static final long serialVersionUID = -5644950968923073649L;

	public NoBooksFoundByBookIdsException(String errorMessage) {
		super(errorMessage);
	}
	
}
