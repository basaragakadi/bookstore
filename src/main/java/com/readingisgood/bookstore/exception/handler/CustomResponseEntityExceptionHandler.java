package com.readingisgood.bookstore.exception.handler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.readingisgood.bookstore.exception.InsufficientStockCountException;
import com.readingisgood.bookstore.exception.NoBooksFoundByBookIdsException;
import com.readingisgood.bookstore.exception.NoOrderFoundException;
import com.readingisgood.bookstore.exception.OrderNotFoundException;
import com.readingisgood.bookstore.exception.OrderNotInsertedException;
import com.readingisgood.bookstore.exception.StockNotFoundException;
import com.readingisgood.bookstore.exception.UnableToGetOrderContentEntitiesException;
import com.readingisgood.bookstore.exception.UnableToInsertOrderContentsException;
import com.readingisgood.bookstore.exception.UserAlreadyExistsException;
import com.readingisgood.bookstore.exception.UserNotFoundException;
import com.readingisgood.bookstore.exception.UserNotInsertedException;
import com.readingisgood.bookstore.model.error.ErrorResponseBody;

/**
 * @author basaragakadi
 *
 * Class for customizing ResponseEntityExceptionHandler
 * Any Exception thrown and reaches to the controller is handled here
 *
 */
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(status.value())
				.timestamp(new Date())
				.message(status.getReasonPhrase())
				.description(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(status.value())
				.timestamp(new Date())
				.message(status.getReasonPhrase())
				.description(ex.getMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, headers, status, request);
	}
	
	@ExceptionHandler(value = { Exception.class})
	protected ResponseEntity<Object> handleGlobalException(
			Exception ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(new Date())
				.message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.description("Oops! Something went wrong!")
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(value = { UserAlreadyExistsException.class})
	protected ResponseEntity<Object> handleUserAlreadyExists(
			UserAlreadyExistsException ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.CONFLICT.value())
				.timestamp(new Date())
				.message("User already exists")
				.description(ex.getMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = { AuthenticationException.class})
	protected ResponseEntity<Object> handleAuthenticationException(
			AuthenticationException ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.UNAUTHORIZED.value())
				.timestamp(new Date())
				.message(ex.getMessage())
				.description("User is not authenticated")
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	}
	
	@ExceptionHandler(value = { InsufficientStockCountException.class})
	protected ResponseEntity<Object> handleInsufficientStockCountException(
			InsufficientStockCountException ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.NOT_FOUND.value())
				.timestamp(new Date())
				.message(HttpStatus.NOT_FOUND.getReasonPhrase())
				.description(ex.getMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { UserNotFoundException.class})
	protected ResponseEntity<Object> handleUserNotFoundException(
			UserNotFoundException ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.NOT_FOUND.value())
				.timestamp(new Date())
				.message(HttpStatus.NOT_FOUND.getReasonPhrase())
				.description(ex.getMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { StockNotFoundException.class})
	protected ResponseEntity<Object> handleStockNotFoundException(
			StockNotFoundException ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.NOT_FOUND.value())
				.timestamp(new Date())
				.message(HttpStatus.NOT_FOUND.getReasonPhrase())
				.description(ex.getMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { OrderNotFoundException.class})
	protected ResponseEntity<Object> handleOrderNotFoundException(
			OrderNotFoundException ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.NOT_FOUND.value())
				.timestamp(new Date())
				.message(HttpStatus.NOT_FOUND.getReasonPhrase())
				.description(ex.getMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { NoBooksFoundByBookIdsException.class})
	protected ResponseEntity<Object> handleNoBooksFoundByBookIdsException(
			NoBooksFoundByBookIdsException ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.NOT_FOUND.value())
				.timestamp(new Date())
				.message(HttpStatus.NOT_FOUND.getReasonPhrase())
				.description(ex.getMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { UnableToGetOrderContentEntitiesException.class})
	protected ResponseEntity<Object> handleUnableToGetOrderContentEntitiesException(
			UnableToGetOrderContentEntitiesException ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.NOT_FOUND.value())
				.timestamp(new Date())
				.message(HttpStatus.NOT_FOUND.getReasonPhrase())
				.description(ex.getMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { UserNotInsertedException.class})
	protected ResponseEntity<Object> handleUserNotInsertedException(
			UserNotInsertedException ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(new Date())
				.message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.description(ex.getMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(value = { UnableToInsertOrderContentsException.class})
	protected ResponseEntity<Object> handleUnableToInsertOrderContentsException(
			UnableToInsertOrderContentsException ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(new Date())
				.message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.description(ex.getMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(value = { OrderNotInsertedException.class})
	protected ResponseEntity<Object> handleOrderNotInsertedException(
			OrderNotInsertedException ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(new Date())
				.message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.description(ex.getMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(value = { NoOrderFoundException.class})
	protected ResponseEntity<Object> handleNoOrderFoundException(
			NoOrderFoundException ex, WebRequest request) {
		ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
				.statusCode(HttpStatus.NOT_FOUND.value())
				.timestamp(new Date())
				.message(HttpStatus.NOT_FOUND.getReasonPhrase())
				.description(ex.getMessage())
				.build();
		return super.handleExceptionInternal(ex, errorResponseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

}
