package com.readingisgood.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readingisgood.bookstore.entity.UserEntity;

/**
 * @author basaragakadi
 *
 * Interface for user repository
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public boolean existsByUsername(String username);
	
	public UserEntity findByUsername(String username);
	
}
