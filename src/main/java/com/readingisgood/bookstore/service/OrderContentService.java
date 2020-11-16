/**
 * 
 */
package com.readingisgood.bookstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.readingisgood.bookstore.entity.BookEntity;
import com.readingisgood.bookstore.entity.OrderContentEntity;
import com.readingisgood.bookstore.entity.OrderEntity;
import com.readingisgood.bookstore.exception.UnableToGetOrderContentEntitiesException;
import com.readingisgood.bookstore.exception.UnableToInsertOrderContentsException;

/**
 * @author basaragakadi
 *
 */
public interface OrderContentService {

	public List<OrderContentEntity> getContentEntities(@Valid @Min(1) Long orderId) throws UnableToGetOrderContentEntitiesException;
	
	public Set<OrderContentEntity> generateOrderContentEntities(List<BookEntity> bookEntities, Map<Long, Integer> bookIdCountMap, OrderEntity orderEntity);

	public List<OrderContentEntity> saveOrderContentEntities(Set<OrderContentEntity> entities) throws UnableToInsertOrderContentsException;

}
