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
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", unique = false, nullable = false)
	private String password;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "name", unique = false, nullable = false)
	private String name;
	
	@Column(name = "surname", unique = false, nullable = false)
	private String surname;
	
	@Column(name = "phone", unique = false, nullable = false)
	private String phone;
	
}
