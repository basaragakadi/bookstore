/**
 * 
 */
package com.readingisgood.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readingisgood.bookstore.entity.OrderEntity;

/**
 * @author basaragakadi
 *
 * Interface for orders repository
 *
 */
@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Long> {

	public List<OrderEntity> findByUserId(Long userId);
	
}
