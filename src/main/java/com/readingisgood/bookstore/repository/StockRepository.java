/**
 * 
 */
package com.readingisgood.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readingisgood.bookstore.entity.StockEntity;

/**
 * @author basaragakadi
 *
 * Interface for stock repository
 *
 */
@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

	public StockEntity findByBookId(Long bookId);
	
	public List<StockEntity> findByBookIdIn(List<Long> bookIds);

}
