/**
 * 
 */
package com.readingisgood.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readingisgood.bookstore.entity.BookEntity;

/**
 * @author basaragakadi
 *
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
