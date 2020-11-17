/**
 * 
 */
package com.readingisgood.bookstore.model.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.readingisgood.bookstore.model.BookOrderModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author basaragakadi
 *
 * Class for representing new order requests
 *
 */
@Data
@Builder
@AllArgsConstructor
public class NewOrderRequest {

	@NotNull(message = "bookOrderModels must not be null")
	@NotEmpty(message = "bookOrderModels must not be empty")
	private final List<BookOrderModel> bookOrderModels;
	
}
