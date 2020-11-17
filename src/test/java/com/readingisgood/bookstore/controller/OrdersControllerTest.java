package com.readingisgood.bookstore.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readingisgood.bookstore.BookstoreApplication;
import com.readingisgood.bookstore.constant.SecurityConstants;
import com.readingisgood.bookstore.entity.OrderEntity;
import com.readingisgood.bookstore.enums.OrderStatus;
import com.readingisgood.bookstore.exception.InsufficientStockCountException;
import com.readingisgood.bookstore.exception.NoOrderFoundException;
import com.readingisgood.bookstore.exception.UserNotFoundException;
import com.readingisgood.bookstore.model.BookOrderModel;
import com.readingisgood.bookstore.model.request.NewOrderRequest;
import com.readingisgood.bookstore.service.OrderContentService;
import com.readingisgood.bookstore.service.OrdersService;
import com.readingisgood.bookstore.util.JwtUtil;

/**
 * @author basaragakadi
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BookstoreApplication.class })
@AutoConfigureMockMvc
@SpringBootTest
class OrdersControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private JwtUtil jwtUtil;

	@MockBean
	private OrdersService ordersService;

	@MockBean
	private OrderContentService orderContentService;

	@WithMockUser(value = "username")
	@Test
	void returnStatusNotFound_whenNoOrderIsFoundForSpecificUser() throws Exception{

		String username = "username";

		when(jwtUtil.extractUsername(any())).thenReturn(username);
		when(ordersService.listAllOrders(any())).thenThrow(new NoOrderFoundException("No order found for user"));

		this.mockMvc.perform(
				get("/orders")
				.header(SecurityConstants.AUTHORIZATION_HEADER_KEY, "Token")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());

	}

	@WithMockUser(value = "username")
	@Test
	void returnStatusNotFound_whenNoUserIsFound() throws Exception{

		String username = "username";

		when(jwtUtil.extractUsername(any())).thenReturn(username);
		when(ordersService.listAllOrders(any())).thenThrow(new UserNotFoundException("User not found"));

		this.mockMvc.perform(
				get("/orders")
				.header(SecurityConstants.AUTHORIZATION_HEADER_KEY, "Token")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());

	}
	
	@WithMockUser(value = "username")
	@Test
	void ordersRetreivedSuccessful_whenRequestIsValid() throws Exception{

		String username = "username";

		List<OrderEntity> orderEntities = getValidOrderEntities();
		
		when(jwtUtil.extractUsername(any())).thenReturn(username);
		when(ordersService.listAllOrders(any())).thenReturn(orderEntities);

		this.mockMvc.perform(
				get("/orders")
				.header(SecurityConstants.AUTHORIZATION_HEADER_KEY, "Token")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}

	@WithMockUser(value = "username")
	@Test
	void returnStatusNotFound_whenInSufficientStock() throws Exception{

		String username = "username";
		NewOrderRequest newOrderRequest = getValidNewOrderRequest();
		
		when(jwtUtil.extractUsername(any())).thenReturn(username);
		when(ordersService.createNewOrder(any(), any())).thenThrow(new InsufficientStockCountException("Insufficient stock"));

		this.mockMvc.perform(
				post("/orders/create")
				.header(SecurityConstants.AUTHORIZATION_HEADER_KEY, "jwt")
				.content(objectMapper.writeValueAsString(newOrderRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());

	}
	
	@WithMockUser(value = "username")
	@Test
	void orderCreatedSuccessfully_whenNewOrderRequestIsValid() throws Exception {

		String username = "username";
		NewOrderRequest newOrderRequest = getValidNewOrderRequest();

		OrderEntity orderEntity = OrderEntity.builder()
			.orderDate(new Date(new java.util.Date().getTime()))
			.status(OrderStatus.PREPARING.toString())
			.totalPrice(new BigDecimal(50))
			.userId(1L)
			.build();

		when(jwtUtil.extractUsername(any())).thenReturn(username);
		when(ordersService.createNewOrder(any(), any())).thenReturn(orderEntity);

		System.out.println(objectMapper.writeValueAsString(newOrderRequest));

		this.mockMvc.perform(
				post("/orders/create")
				.header(SecurityConstants.AUTHORIZATION_HEADER_KEY, "jwt")
				.content(objectMapper.writeValueAsString(newOrderRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}
	
	@WithMockUser(value = "username")
	@Test
	void returnStatusBadRequest_whenRequestHeaderIsMissing() throws Exception {

		String username = "username";
		NewOrderRequest newOrderRequest = getValidNewOrderRequest();

		OrderEntity orderEntity = OrderEntity.builder()
			.orderDate(new Date(new java.util.Date().getTime()))
			.status(OrderStatus.PREPARING.toString())
			.totalPrice(new BigDecimal(50))
			.userId(1L)
			.build();

		when(jwtUtil.extractUsername(any())).thenReturn(username);
		when(ordersService.createNewOrder(any(), any())).thenReturn(orderEntity);

		System.out.println(objectMapper.writeValueAsString(newOrderRequest));

		this.mockMvc.perform(
				post("/orders/create")
				.content(objectMapper.writeValueAsString(newOrderRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

	}
	
	@WithMockUser(value = "username")
	@Test
	void returnStatusBadRequest_whenNewOrderRequestIsInValid() throws Exception {

		String username = "username";
		NewOrderRequest newOrderRequest = getInValidNewOrderRequest();

		OrderEntity orderEntity = OrderEntity.builder()
			.orderDate(new Date(new java.util.Date().getTime()))
			.status(OrderStatus.PREPARING.toString())
			.totalPrice(new BigDecimal(50))
			.userId(1L)
			.build();

		when(jwtUtil.extractUsername(any())).thenReturn(username);
		when(ordersService.createNewOrder(any(), any())).thenReturn(orderEntity);

		System.out.println(objectMapper.writeValueAsString(newOrderRequest));

		this.mockMvc.perform(
				post("/orders/create")
				.content(objectMapper.writeValueAsString(newOrderRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

	}

	private NewOrderRequest getInValidNewOrderRequest() {

		List<BookOrderModel> bookOrderModels = Collections.emptyList();

		return NewOrderRequest.builder()
				.bookOrderModels(bookOrderModels)
				.build();


	}

	private NewOrderRequest getValidNewOrderRequest() {

		List<BookOrderModel> bookOrderModels = new ArrayList<>();
		bookOrderModels.add(BookOrderModel.builder().bookId(1L).count(1).build());

		return NewOrderRequest.builder()
				.bookOrderModels(bookOrderModels)
				.build();


	}
	
	private List<OrderEntity> getValidOrderEntities() {
		
		OrderEntity orderEntity = OrderEntity.builder()
				.orderDate(new Date(new java.util.Date().getTime()))
				.status(OrderStatus.PREPARING.toString())
				.totalPrice(new BigDecimal(50))
				.userId(1L)
				.build();
		
		List<OrderEntity> orderEntities = new ArrayList<>();
		orderEntities.add(orderEntity);
		
		return orderEntities;
		
	}

}
