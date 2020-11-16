/**
 * 
 */
package com.readingisgood.bookstore.service;

import java.util.List;

import com.readingisgood.bookstore.entity.StockEntity;
import com.readingisgood.bookstore.exception.InsufficientStockCountException;
import com.readingisgood.bookstore.exception.StockNotFoundException;
import com.readingisgood.bookstore.model.BookOrderModel;

/**
 * @author basaragakadi
 *
 */
public interface StockService {

	public List<StockEntity> getStockEntitiesByBookIds(List<Long> bookIds);
	
	public boolean isStockCountSufficient(List<BookOrderModel> bookOrderModels) throws StockNotFoundException;
	
	public void updateStockCountAfterNewOrder(List<BookOrderModel> bookOrderModels) throws InsufficientStockCountException, StockNotFoundException;
	
}
