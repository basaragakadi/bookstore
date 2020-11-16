/**
 * 
 */
package com.readingisgood.bookstore.model.response;

import java.util.List;

import com.readingisgood.bookstore.entity.OrderEntity;

import lombok.Builder;
import lombok.Data;

/**
 * @author basaragakadi
 *
 * Class for representing order response
 *
 */
@Data
@Builder
public class OrdersRespone {

	private final String username;
	private final List<OrderEntity> orders;
	
}
