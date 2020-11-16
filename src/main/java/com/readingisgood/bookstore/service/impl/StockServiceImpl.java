/**
 * 
 */
package com.readingisgood.bookstore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;

import com.readingisgood.bookstore.entity.StockEntity;
import com.readingisgood.bookstore.exception.InsufficientStockCountException;
import com.readingisgood.bookstore.exception.StockNotFoundException;
import com.readingisgood.bookstore.model.BookOrderModel;
import com.readingisgood.bookstore.repository.StockRepository;
import com.readingisgood.bookstore.service.StockService;

import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 *
 */
@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService {

	private final StockRepository stockRepository;
	
	@Override
	public List<StockEntity> getStockEntitiesByBookIds(List<Long> bookIds) throws Exception {
		return stockRepository.findByBookIdIn(bookIds);
	}

	@Override
	public boolean isStockCountSufficient(@Valid @NotBlank(message = "bookOrderModels can not be blank") List<BookOrderModel> bookOrderModels)
			throws Exception {
		List<Long> bookIds = bookOrderModels.stream()
			.map(BookOrderModel::getBookId)
			.collect(Collectors.toList());
		List<StockEntity> stockEntities = getStockEntitiesByBookIds(bookIds);
		
		if(stockEntities.size() < bookIds.size()) {
			List<Long> bookIdsInStock = 
					stockEntities.stream()
						.map(StockEntity::getBookId)
						.collect(Collectors.toList());
			bookIds.removeAll(bookIdsInStock);
			throw new StockNotFoundException("Stock records are missing for bookIds: " + bookIds.toString());
		}
		
		for (StockEntity stockEntity : stockEntities) {
			for (BookOrderModel bookOrderModel : bookOrderModels) {
				if(bookOrderModel.getBookId().equals(stockEntity.getBookId()) && stockEntity.getCount().compareTo(bookOrderModel.getCount()) < 0)
					return false;
			}
		}
		
		return true;
	}

	@Override
	public void updateStockCountAfterNewOrder(List<BookOrderModel> bookOrderModels) throws Exception {
		
		if(!isStockCountSufficient(bookOrderModels))
			throw new InsufficientStockCountException("Insufficient stock");
		
		List<Long> bookIds = bookOrderModels.stream()
				.map(BookOrderModel::getBookId)
				.collect(Collectors.toList());
		
		List<StockEntity> stockEntities = stockRepository.findByBookIdIn(bookIds);
		
		for (StockEntity stockEntity : stockEntities) {
			for(BookOrderModel bookOrderModel : bookOrderModels) {
				if(bookOrderModel.getBookId().equals(stockEntity.getBookId()))
					stockEntity.setCount(stockEntity.getCount() - bookOrderModel.getCount());
			}
		}
		
		stockRepository.saveAll(stockEntities);
		
	}
	
}
