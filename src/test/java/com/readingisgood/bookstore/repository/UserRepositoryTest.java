/**
 * 
 */
package com.readingisgood.bookstore.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.readingisgood.bookstore.entity.UserEntity;

/**
 * @author basaragakadi
 *
 */
@DataJpaTest
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	void testExistsByUsername() throws Exception{
		UserEntity userEntity = UserEntity.builder()
			.email("user@user.com")
			.name("user")
			.password("password")
			.phone("0000000000")
			.surname("user")
			.username("user")
			.build();
		
		userRepository.save(userEntity);
	}

	
	void testFindByUsername() {
		
		UserEntity userEntity = UserEntity.builder()
			.email("user@user.com")
			.name("user")
			.password("password")
			.phone("0000000000")
			.surname("user")
			.username("user")
			.build();
		
		userRepository.save(userEntity);
		
		userEntity = userRepository.findByUsername(userEntity.getUsername());
		assertNotNull(userEntity);
		
	}

}
