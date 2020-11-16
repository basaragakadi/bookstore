/**
 * 
 */
package com.readingisgood.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author basaragakadi
 *
 * Class for representing book order model for new orders
 *
 */
@Data
@AllArgsConstructor
public class BookOrderModel {

	private final Long bookId;
	private final Integer count;
	
}
