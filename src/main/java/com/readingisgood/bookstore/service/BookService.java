/**
 * 
 */
package com.readingisgood.bookstore.service;

import java.util.List;

import com.readingisgood.bookstore.entity.BookEntity;
import com.readingisgood.bookstore.exception.NoBooksFoundByBookIdsException;

/**
 * @author basaragakadi
 *
 */
public interface BookService {

	public List<BookEntity> getAllBooksByIds(List<Long> bookIds) throws NoBooksFoundByBookIdsException;
	
}
