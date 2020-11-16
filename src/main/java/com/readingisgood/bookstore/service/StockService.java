/**
 * 
 */
package com.readingisgood.bookstore.service;

import java.util.List;

import com.readingisgood.bookstore.entity.StockEntity;
import com.readingisgood.bookstore.model.BookOrderModel;

/**
 * @author basaragakadi
 *
 */
public interface StockService {

	public List<StockEntity> getStockEntitiesByBookIds(List<Long> bookIds) throws Exception;
	
	public boolean isStockCountSufficient(List<BookOrderModel> bookOrderModels) throws Exception;
	
	public void updateStockCountAfterNewOrder(List<BookOrderModel> bookOrderModels) throws Exception;
	
}
