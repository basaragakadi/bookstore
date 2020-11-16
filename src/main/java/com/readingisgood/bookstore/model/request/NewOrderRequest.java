/**
 * 
 */
package com.readingisgood.bookstore.model.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.readingisgood.bookstore.model.BookOrderModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author basaragakadi
 *
 * Class for representing new order requests
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderRequest {

	@NotNull(message = "bookOrderModels must not be null")
	@NotEmpty(message = "bookOrderModels must not be empty")
	private List<BookOrderModel> bookOrderModels;
	
}
