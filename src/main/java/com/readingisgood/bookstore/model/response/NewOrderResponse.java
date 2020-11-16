/**
 * 
 */
package com.readingisgood.bookstore.model.response;

import com.readingisgood.bookstore.entity.OrderEntity;

import lombok.Builder;
import lombok.Data;

/**
 * @author basaragakadi
 *
 */
@Data
@Builder
public class NewOrderResponse {

	private final String username;
	private final OrderEntity orderEntity;
	
}
