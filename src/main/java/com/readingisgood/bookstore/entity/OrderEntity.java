/**
 * 
 */
package com.readingisgood.bookstore.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author basaragakadi
 *
 * Entity class for orders table in h2 database
 *
 */
@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "user_id", unique = false, nullable = false)
	private Long userId;
	
	@Column(name = "order_date", unique = false, nullable = false)
	private Date orderDate;
	
	@Column(name = "total_price", unique = false, nullable = false)
	private BigDecimal totalPrice;
	
	@Column(name = "status", unique = false, nullable = false)
	private String status;
	
}
