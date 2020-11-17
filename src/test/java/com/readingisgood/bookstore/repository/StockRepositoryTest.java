/**
 * 
 */
package com.readingisgood.bookstore.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.readingisgood.bookstore.entity.StockEntity;

/**
 * @author basaragakadi
 *
 */
@DataJpaTest
class StockRepositoryTest {

	@Autowired
	private StockRepository stockRepository;
	
	@Test
	void testFindByBookId() {
		
		StockEntity stockEntity = StockEntity.builder()
				.bookId(1L)
				.count(5)
				.id(1L)
				.build();
		
		stockRepository.save(stockEntity);
		
		stockEntity = stockRepository.findByBookId(stockEntity.getBookId());
		
		assertNotNull(stockEntity);
		
	}

	@Test
	void testFindByBookIdIn() {
		
		StockEntity stockEntity = StockEntity.builder()
				.bookId(1L)
				.count(5)
				.id(1L)
				.build();
		
		StockEntity stockEntity2 = StockEntity.builder()
				.bookId(2L)
				.count(5)
				.id(2L)
				.build();
		
		List<StockEntity> stockEntities = new ArrayList<>();
		stockEntities.add(stockEntity);
		stockEntities.add(stockEntity2);
		
		List<Long> bookIds = stockEntities.stream()
				.map(StockEntity::getBookId)
				.collect(Collectors.toList());
		
		assertThat(stockRepository.findByBookIdIn(bookIds).size() == 0);
		
		stockRepository.saveAll(stockEntities);
		
		stockEntities = stockRepository.findByBookIdIn(bookIds);
		
		assertNotNull(stockEntities);
		assertThat(stockEntities.size() > 0);
		assertThat(stockEntities.size() == bookIds.size());
		
	}

}
