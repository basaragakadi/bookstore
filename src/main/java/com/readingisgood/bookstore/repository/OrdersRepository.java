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
 */
@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Long> {

	public List<OrderEntity> findByUserId(Long userId);
	
}
