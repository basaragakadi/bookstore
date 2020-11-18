/**
 * 
 */
package com.readingisgood.bookstore.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.readingisgood.bookstore.entity.OrderContentEntity;
import com.readingisgood.bookstore.entity.OrderEntity;
import com.readingisgood.bookstore.entity.UserEntity;
import com.readingisgood.bookstore.enums.OrderStatus;

/**
 * @author basaragakadi
 *
 */
@DataJpaTest
class OrderContentRepositoryTest {

	@Autowired
	private OrderContentRepository orderContentRepository;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void testFindByOrderId() {
		
		UserEntity userEntity = UserEntity.builder()
			.email("user@user.com")
			.name("user")
			.password("password")
			.phone("0000000000")
			.surname("user")
			.username("user")
			.build();
		
		userRepository.save(userEntity);
		
		OrderEntity orderEntity = OrderEntity.builder()
				.orderDate(new Date(new java.util.Date().getTime()))
				.status(OrderStatus.PREPARING.toString())
				.totalPrice(new BigDecimal(50))
				.userId(userEntity.getId())
				.build();
		
		ordersRepository.save(orderEntity);
		
		OrderContentEntity orderContentEntity = OrderContentEntity.builder()
				.bookId(1L)
				.count(3)
				.orderId(orderEntity.getId())
				.price(new BigDecimal(35))
				.build();
		
		OrderContentEntity orderContentEntity2 = OrderContentEntity.builder()
				.bookId(2L)
				.count(1)
				.orderId(orderEntity.getId())
				.price(new BigDecimal(45))
				.build();
		
		List<OrderContentEntity> orderContentEntities = new ArrayList<>();
		orderContentEntities.add(orderContentEntity);
		orderContentEntities.add(orderContentEntity2);
		
		orderContentRepository.saveAll(orderContentEntities);
		
		List<OrderContentEntity> orderContentEntities2 = orderContentRepository.findByOrderId(orderContentEntity.getOrderId());
		
		assertNotNull(orderContentEntities2);
		assertTrue(orderContentEntities.size() > 0);
		
	}

}
