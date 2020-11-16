/**
 * 
 */
package com.readingisgood.bookstore.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readingisgood.bookstore.constant.SecurityConstants;
import com.readingisgood.bookstore.model.request.NewOrderRequest;
import com.readingisgood.bookstore.model.response.NewOrderResponse;
import com.readingisgood.bookstore.model.response.OrderContentResponse;
import com.readingisgood.bookstore.model.response.OrdersRespone;
import com.readingisgood.bookstore.service.OrdersService;
import com.readingisgood.bookstore.util.JwtUtil;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 * 
 * Controller class for handling orders endpoints
 * Endpoints require authorization header to extract username out of it and completes the operation with that unique username
 * 
 */
@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

	private JwtUtil jwtUtil;
	private OrdersService ordersService;
	
	@ApiOperation("Lists all orders of autheticated user")
	@GetMapping
	public ResponseEntity<?> listAllOrders(@RequestHeader (name=SecurityConstants.AUTHORIZATION_HEADER_KEY) String authorizationHeader) throws Exception{
		String username = jwtUtil.extractUsernameFromAuthorizationHeader(authorizationHeader);
		return ResponseEntity.ok(
				OrdersRespone.builder()
					.username(username)
					.orders(ordersService.listAllOrders(username))
					.build()
				);
	}
	
	@ApiOperation("Creates a new order for authenticated user")
	@PostMapping("/create")
	public ResponseEntity<?> createNewOrder(@RequestHeader (name=SecurityConstants.AUTHORIZATION_HEADER_KEY) String authorizationHeader,
			@Valid @RequestBody NewOrderRequest newOrderRequest) throws Exception{
		String username = jwtUtil.extractUsernameFromAuthorizationHeader(authorizationHeader);
		return ResponseEntity.ok(
				NewOrderResponse.builder()
					.username(username)
					.orderEntity(ordersService.createNewOrder(username, newOrderRequest))
					.build()
				);
	}
	
	@ApiOperation("Lists a specific order's content of authenticated user")
	@GetMapping("/{order_id}")
	public ResponseEntity<?> getOrderContent(@RequestHeader (name=SecurityConstants.AUTHORIZATION_HEADER_KEY) String authorizationHeader,
			@PathVariable("order_id") @Valid @Min(1) Long orderId) throws Exception {
		String username = jwtUtil.extractUsernameFromAuthorizationHeader(authorizationHeader);
		return ResponseEntity.ok(
				OrderContentResponse.builder()
				.username(username)
				.orderEntity(ordersService.getOrderByOrderId(orderId))
				.orderContentEntities(ordersService.getContentEntities(orderId))
				.build()
				);
		
	}
	
}
