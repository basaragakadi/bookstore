/**
 * 
 */
package com.readingisgood.bookstore.service;

import java.util.List;

import com.readingisgood.bookstore.entity.BookEntity;

/**
 * @author basaragakadi
 *
 */
public interface BookService {

	public List<BookEntity> getAllBooksByIds(List<Long> bookIds);
	
}
