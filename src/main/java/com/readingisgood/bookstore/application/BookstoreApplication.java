package com.readingisgood.bookstore.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author basaragakadi
 *
 */
@SpringBootApplication
@ComponentScan("com.readingisgood.bookstore")
@EntityScan("com.readingisgood.bookstore.entity")
@EnableJpaRepositories("com.readingisgood.bookstore.repository")
public class BookstoreApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		
		LOGGER.info("Running on Java version {}", System.getProperty("java.version"));
		SpringApplication.run(BookstoreApplication.class, args);
		
	}

}