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
	
	@Column(name = "country", unique = false, nullable = false)
	private String country;
	
	@Column(name = "city", unique = false, nullable = false)
	private String city;
	
	@Column(name = "details", unique = false, nullable = false)
	private String details;
	
	@Column(name = "user_id", unique = false, nullable = false)
	private Long userId;
	
}
