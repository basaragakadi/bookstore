/**
 * 
 */
package com.readingisgood.bookstore.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.readingisgood.bookstore.entity.OrderEntity;
import com.readingisgood.bookstore.entity.UserEntity;
import com.readingisgood.bookstore.enums.OrderStatus;

/**
 * @author basaragakadi
 *
 */
@DataJpaTest
class OrdersRepositoryTest {
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Test
	void testFindByUserId() throws Exception {
		
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
		
		OrderEntity orderEntity2 = OrderEntity.builder()
				.orderDate(new Date(new java.util.Date().getTime()))
				.status(OrderStatus.PREPARING.toString())
				.totalPrice(new BigDecimal(25))
				.userId(userEntity.getId())
				.build();
		
		List<OrderEntity> orderEntities = new ArrayList<>();
		orderEntities.add(orderEntity);
		orderEntities.add(orderEntity2);
		
		ordersRepository.saveAll(orderEntities);
		
		ordersRepository.findByUserId(1L);
		
	}

}
