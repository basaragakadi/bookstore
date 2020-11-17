/**
 * 
 */
package com.readingisgood.bookstore.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author basaragakadi
 *
 * Class for representing book order model for new orders
 *
 */
@Data
@Builder
@RequiredArgsConstructor
public class BookOrderModel {

	private final Long bookId;
	private final Integer count;
	
}
