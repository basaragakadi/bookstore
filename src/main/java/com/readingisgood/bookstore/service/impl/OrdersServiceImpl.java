/**
 * 
 */
package com.readingisgood.bookstore.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.readingisgood.bookstore.entity.BookEntity;
import com.readingisgood.bookstore.entity.OrderEntity;
import com.readingisgood.bookstore.entity.UserEntity;
import com.readingisgood.bookstore.enums.OrderStatus;
import com.readingisgood.bookstore.exception.InsufficientStockCountException;
import com.readingisgood.bookstore.exception.NoBooksFoundByBookIdsException;
import com.readingisgood.bookstore.exception.OrderNotFoundException;
import com.readingisgood.bookstore.exception.OrderNotInsertedException;
import com.readingisgood.bookstore.exception.StockNotFoundException;
import com.readingisgood.bookstore.exception.NoOrderFoundException;
import com.readingisgood.bookstore.exception.UnableToInsertOrderContentsException;
import com.readingisgood.bookstore.exception.UserNotFoundException;
import com.readingisgood.bookstore.model.BookOrderModel;
import com.readingisgood.bookstore.model.request.NewOrderRequest;
import com.readingisgood.bookstore.repository.OrdersRepository;
import com.readingisgood.bookstore.service.BookService;
import com.readingisgood.bookstore.service.OrderContentService;
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

	private final UserService userService;
	private final StockService stockService;
	private final BookService bookService;
	private final OrderContentService orderContentService;

	@Override
	public OrderEntity getOrderByOrderId(Long orderId) throws OrderNotFoundException {
		Optional<OrderEntity> optionalOrderEntity = ordersRepository.findById(orderId);

		if(optionalOrderEntity.isEmpty())
			throw new OrderNotFoundException("Order not found with orderId: " + orderId);

		return optionalOrderEntity.get();
	}

	@Override
	public List<OrderEntity> listAllOrders(String username) throws UserNotFoundException, NoOrderFoundException{
		
		UserEntity userEntity = userService.getUserByUsername(username);
		List<OrderEntity> orderEntities = ordersRepository.findByUserId(userEntity.getId());
		
		if(orderEntities.isEmpty())
			throw new NoOrderFoundException("No order found for userId: " + userEntity.getId().toString());
		
		return orderEntities;
	}

	@Transactional
	@Override
	public OrderEntity createNewOrder(String username, @Valid NewOrderRequest newOrderRequest)
			throws InsufficientStockCountException, UnableToInsertOrderContentsException, NoBooksFoundByBookIdsException, StockNotFoundException, UserNotFoundException, OrderNotInsertedException{

		List<BookOrderModel> bookOrderModels = newOrderRequest.getBookOrderModels();

		if(!stockService.isStockCountSufficient(bookOrderModels))
			throw new InsufficientStockCountException("Insufficient stock");

		Map<Long, Integer> bookIdCountMap = getBookIdCountMap(bookOrderModels);
		List<Long> bookIds = extractBookIdsFromBookIdCountMap(bookIdCountMap);

		List<BookEntity> bookEntities = bookService.getAllBooksByIds(bookIds);

		UserEntity userEntity = userService.getUserByUsername(username);

		OrderEntity orderEntity = OrderEntity.builder()
				.userId(userEntity.getId())
				.orderDate(new java.sql.Date(new Date().getTime()))
				.totalPrice(calculateTotalPrice(bookEntities, bookIdCountMap))
				.status(OrderStatus.PREPARING.toString())
				.build();

		final OrderEntity finalorderEntity = ordersRepository.saveAndFlush(orderEntity);

		orderContentService.saveOrderContentEntities(
				orderContentService.generateOrderContentEntities(bookEntities, bookIdCountMap, finalorderEntity)
				);
		
		stockService.updateStockCountAfterNewOrder(bookOrderModels);

		return finalorderEntity;

	}

	private BigDecimal calculateTotalPrice(List<BookEntity> bookEntities, Map<Long, Integer> bookIdCountMap) {
		return bookEntities.stream()
				.map(e -> e.getPrice().multiply(new BigDecimal(bookIdCountMap.get(e.getId()))))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private Map<Long, Integer> getBookIdCountMap(List<BookOrderModel> bookOrderModels) {
		return bookOrderModels.stream()
				.collect(Collectors.toMap(BookOrderModel::getBookId, BookOrderModel::getCount));
	}

	private List<Long> extractBookIdsFromBookIdCountMap(Map<Long, Integer> bookIdCountMap) {
		return bookIdCountMap.keySet().stream()
				.collect(Collectors.toList());
	}
	
}
