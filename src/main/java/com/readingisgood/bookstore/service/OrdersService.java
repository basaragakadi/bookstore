/**
 * 
 */
package com.readingisgood.bookstore.service;

import java.util.List;

import javax.validation.Valid;

import com.readingisgood.bookstore.entity.OrderEntity;
import com.readingisgood.bookstore.exception.InsufficientStockCountException;
import com.readingisgood.bookstore.exception.NoBooksFoundByBookIdsException;
import com.readingisgood.bookstore.exception.OrderNotInsertedException;
import com.readingisgood.bookstore.exception.StockNotFoundException;
import com.readingisgood.bookstore.exception.NoOrderFoundException;
import com.readingisgood.bookstore.exception.OrderNotFoundException;
import com.readingisgood.bookstore.exception.UnableToInsertOrderContentsException;
import com.readingisgood.bookstore.exception.UserNotFoundException;
import com.readingisgood.bookstore.model.request.NewOrderRequest;

/**
 * @author basaragakadi
 *
 */
public interface OrdersService {

	public OrderEntity getOrderByOrderId(Long orderId) throws OrderNotFoundException;

	public List<OrderEntity> listAllOrders(String username) throws UserNotFoundException, NoOrderFoundException;

	public OrderEntity createNewOrder(String username, @Valid NewOrderRequest newOrderRequest)
			throws InsufficientStockCountException, UnableToInsertOrderContentsException, NoBooksFoundByBookIdsException, StockNotFoundException, UserNotFoundException, OrderNotInsertedException;

}
