/**
 * 
 */
package com.readingisgood.bookstore.entity;

import java.math.BigDecimal;

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
 * Entity class for order_content table in h2 database
 * 
 */
@Entity
@Table(name = "order_content")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderContentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "order_id", nullable = false)
	private Long orderId;
	
	@Column(name = "book_id", nullable = false)
	private Long bookId;
	
	@Column(name = "count", nullable = false)
	private Integer count;
	
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	
}
