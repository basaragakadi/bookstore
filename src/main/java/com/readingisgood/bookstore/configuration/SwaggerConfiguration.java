/**
 * 
 */
package com.readingisgood.bookstore.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author basaragakadi
 *
 * Class for configuring Swagger.
 * url: localhost:8080/swagger-ui
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.readingisgood.bookstore"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		
		return new ApiInfo(
				"Bookstore Backend Applicaiton", 
				"Basic Bookstore API",
				"v1",
				"Terms Of Service Url",
				new Contact("Basar Agakadi", "https://github.com/basaragakadi", "basaragakadi@gmail.com"),
				"",
				"",
				Collections.emptyList()
				);
		
	}
	
}
