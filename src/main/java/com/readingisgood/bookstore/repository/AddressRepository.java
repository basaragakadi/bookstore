/**
 * 
 */
package com.readingisgood.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readingisgood.bookstore.entity.AddressEntity;

/**
 * @author basaragakadi
 *
 */
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
