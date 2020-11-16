/**
 * 
 */
package com.readingisgood.bookstore.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.readingisgood.bookstore.entity.BookEntity;
import com.readingisgood.bookstore.exception.NoBooksFoundByBookIdsException;
import com.readingisgood.bookstore.repository.BookRepository;
import com.readingisgood.bookstore.service.BookService;

import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 *
 */
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	
	@Override
	public List<BookEntity> getAllBooksByIds(List<Long> bookIds) throws NoBooksFoundByBookIdsException {
		List<BookEntity> bookEntities = bookRepository.findAllById(bookIds);
		
		if(bookEntities.isEmpty())
			throw new NoBooksFoundByBookIdsException("No book entities found with bookIds: " + bookIds.toString());
		
		return bookEntities;
		
	}

}
