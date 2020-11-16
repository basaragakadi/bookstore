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
 */
@Entity
@Table(name = "book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "title", unique = false, nullable = false)
	private String title;
	
	@Column(name = "author", unique = false, nullable = false)
	private String author;
	
	@Column(name = "price", unique = false, nullable = false)
	private BigDecimal price;
	
}
