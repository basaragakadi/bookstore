/**
 * 
 */
package com.readingisgood.bookstore.model.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.readingisgood.bookstore.model.BookOrderModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author basaragakadi
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderRequest {

	@NotNull(message = "bookOrderModels must not be null")
	private List<BookOrderModel> bookOrderModels;
	
}
