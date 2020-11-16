/**
 * 
 */
package com.readingisgood.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readingisgood.bookstore.entity.OrderContentEntity;

/**
 * @author basaragakadi
 *
 */
@Repository
public interface OrderContentRepository extends JpaRepository<OrderContentEntity, Long> {

	public List<OrderContentEntity> findByOrderId(Long orderId);
	
}
