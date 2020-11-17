/**
 * 
 */
package com.readingisgood.bookstore.service.impl;

import org.springframework.stereotype.Service;

import com.readingisgood.bookstore.entity.AddressEntity;
import com.readingisgood.bookstore.repository.AddressRepository;
import com.readingisgood.bookstore.service.AddressService;

import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 *
 */
@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;
	
	@Override
	public AddressEntity saveAddress(AddressEntity addressEntity) {
		return addressRepository.saveAndFlush(addressEntity);
	}

}
