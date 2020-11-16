/**
 * 
 */
package com.readingisgood.bookstore.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Service;

import com.readingisgood.bookstore.entity.BookEntity;
import com.readingisgood.bookstore.entity.OrderContentEntity;
import com.readingisgood.bookstore.entity.OrderEntity;
import com.readingisgood.bookstore.exception.UnableToGetOrderContentEntitiesException;
import com.readingisgood.bookstore.exception.UnableToInsertOrderContentsException;
import com.readingisgood.bookstore.repository.OrderContentRepository;
import com.readingisgood.bookstore.service.OrderContentService;

import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 *
 */
@Service
@AllArgsConstructor
public class OrderContentServiceImpl implements OrderContentService {

	private final OrderContentRepository orderContentRepository;
	
	@Override
	public List<OrderContentEntity> getContentEntities(@Valid @Min(1) Long orderId) throws UnableToGetOrderContentEntitiesException{
		
		List<OrderContentEntity> orderContentEntities = orderContentRepository.findByOrderId(orderId);
		
		if(orderContentEntities.isEmpty())
			throw new UnableToGetOrderContentEntitiesException("Could not find order contents");
		
		return orderContentEntities;
		
	}
	
	@Override
	public Set<OrderContentEntity> generateOrderContentEntities(List<BookEntity> bookEntities,
			Map<Long, Integer> bookIdCountMap, OrderEntity orderEntity) {
		return bookEntities.stream()
				.map(e -> OrderContentEntity.builder()
						.orderId(orderEntity.getId())
						.bookId(e.getId())
						.count(bookIdCountMap.get(e.getId()))
						.price(e.getPrice())
						.build())
				.collect(Collectors.toSet());
	}

	@Override
	public List<OrderContentEntity> saveOrderContentEntities(Set<OrderContentEntity> orderContentEntities) throws UnableToInsertOrderContentsException{
		return orderContentRepository.saveAll(orderContentEntities);
	}

}
