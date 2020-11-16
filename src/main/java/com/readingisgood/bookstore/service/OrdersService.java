/**
 * 
 */
package com.readingisgood.bookstore.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.readingisgood.bookstore.entity.OrderContentEntity;
import com.readingisgood.bookstore.entity.OrderEntity;
import com.readingisgood.bookstore.model.request.NewOrderRequest;

/**
 * @author basaragakadi
 *
 */
public interface OrdersService {

	/**
	 * @param orderId
	 * @return
	 */
	public OrderEntity getOrderByOrderId(Long orderId) throws Exception;
	
	/**
	 * @param username
	 * @return
	 */
	public List<OrderEntity> listAllOrders(String username) throws Exception;

	/**
	 * @param username
	 * @param newOrderRequest
	 * @return
	 */
	public OrderEntity createNewOrder(String username, @Valid NewOrderRequest newOrderRequest) throws Exception;

	/**
	 * @param orderId
	 * @return
	 */
	public List<OrderContentEntity> getContentEntities(@Valid @Min(1) Long orderId) throws Exception;

}
