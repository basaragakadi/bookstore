/**
 * 
 */
package com.readingisgood.bookstore.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.readingisgood.bookstore.entity.BookEntity;
import com.readingisgood.bookstore.entity.OrderContentEntity;
import com.readingisgood.bookstore.entity.OrderEntity;
import com.readingisgood.bookstore.entity.UserEntity;
import com.readingisgood.bookstore.enums.OrderStatus;
import com.readingisgood.bookstore.exception.InsufficientStockCountException;
import com.readingisgood.bookstore.exception.OrderNotFoundException;
import com.readingisgood.bookstore.model.BookOrderModel;
import com.readingisgood.bookstore.model.request.NewOrderRequest;
import com.readingisgood.bookstore.repository.OrderContentRepository;
import com.readingisgood.bookstore.repository.OrdersRepository;
import com.readingisgood.bookstore.service.BookService;
import com.readingisgood.bookstore.service.OrdersService;
import com.readingisgood.bookstore.service.StockService;
import com.readingisgood.bookstore.service.UserService;

import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 *
 */
@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {

	private final OrdersRepository ordersRepository;
	private final OrderContentRepository orderContentRepository;

	private final UserService userService;
	private final StockService stockService;
	private final BookService bookService;

	@Override
	public OrderEntity getOrderByOrderId(Long orderId) throws Exception {
		Optional<OrderEntity> optionalOrderEntity = ordersRepository.findById(orderId);
		
		if(optionalOrderEntity.isEmpty())
			throw new OrderNotFoundException("Order not found with orderId: " + orderId);
		
		return optionalOrderEntity.get();
	}
	
	@Override
	public List<OrderEntity> listAllOrders(String username) throws Exception{
		UserEntity userEntity = userService.getUserByUsername(username);
		return ordersRepository.findByUserId(userEntity.getId());
	}

	@Transactional
	@Override
	public OrderEntity createNewOrder(String username, @Valid NewOrderRequest newOrderRequest) throws Exception{

		List<BookOrderModel> bookOrderModels = newOrderRequest.getBookOrderModels();

		if(!stockService.isStockCountSufficient(bookOrderModels))
			throw new InsufficientStockCountException("Insufficient stock");

		Map<Long, Integer> bookIdCountMap = bookOrderModels.stream()
				.collect(Collectors.toMap(BookOrderModel::getBookId, BookOrderModel::getCount));

		List<Long> bookIds = bookIdCountMap.keySet().stream()
				.collect(Collectors.toList());

		List<BookEntity> bookEntities = bookService.getAllBooksByIds(bookIds);

		BigDecimal totalPrice = calculateTotalPrice(bookEntities, bookIdCountMap);
		
		stockService.updateStockCountAfterNewOrder(bookOrderModels);

		UserEntity userEntity = userService.getUserByUsername(username);

		OrderEntity orderEntity = OrderEntity.builder()
				.userId(userEntity.getId())
				.orderDate(new java.sql.Date(new Date().getTime()))
				.totalPrice(totalPrice)
				.status(OrderStatus.PREPARING.toString())
				.build();

		final OrderEntity finalorderEntity = ordersRepository.save(orderEntity);

		Set<OrderContentEntity> orderContentEntities = bookEntities.stream()
				.map(e -> OrderContentEntity.builder()
						.orderId(finalorderEntity.getId())
						.bookId(e.getId())
						.count(bookIdCountMap.get(e.getId()))
						.price(e.getPrice())
						.build())
				.collect(Collectors.toSet());

		orderContentRepository.saveAll(orderContentEntities);
		
		return finalorderEntity;

	}

	/**
	 * @param bookEntities
	 * @param bookIdCountMap
	 * @return
	 */
	private BigDecimal calculateTotalPrice(List<BookEntity> bookEntities, Map<Long, Integer> bookIdCountMap) {
		return bookEntities.stream()
				.map(e -> e.getPrice().multiply(new BigDecimal(bookIdCountMap.get(e.getId()))))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public List<OrderContentEntity> getContentEntities(@Valid @Min(1) Long orderId) throws Exception{
		 return orderContentRepository.findByOrderId(orderId);
	}

}
