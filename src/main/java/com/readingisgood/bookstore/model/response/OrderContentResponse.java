/**
 * 
 */
package com.readingisgood.bookstore.model.response;

import java.util.List;

import com.readingisgood.bookstore.entity.OrderContentEntity;
import com.readingisgood.bookstore.entity.OrderEntity;

import lombok.Builder;
import lombok.Data;

/**
 * @author basaragakadi
 *
 */
@Data
@Builder
public class OrderContentResponse {

	private final String username;
	private final OrderEntity orderEntity;
	private final List<OrderContentEntity> orderContentEntities;
	
}
