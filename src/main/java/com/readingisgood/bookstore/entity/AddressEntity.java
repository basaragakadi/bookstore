/**
 * 
 */
package com.readingisgood.bookstore.entity;

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
 * Entity claass for address table in h2 database
 *
 */
@Entity
@Table(name = "address")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "details", nullable = false)
	private String details;
	
	@Column(name = "user_id", nullable = false)
	private Long userId;
	
}
