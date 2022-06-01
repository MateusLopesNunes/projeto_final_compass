package com.br.compass.config.error;

import java.util.NoSuchElementException;

import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.br.compass.dto.ExceptionResponseDto;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> exceptionNoSuchElement(NoSuchElementException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> exceptionTypeMismatch(MethodArgumentTypeMismatchException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> exceptionResultDataAccess(EmptyResultDataAccessException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConverterNotFoundException.class)
	public ResponseEntity<Object> exceptionConverterNotFound(ConverterNotFoundException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> exceptionMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.METHOD_NOT_ALLOWED.value(), exception.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> exceptionMethodNotSupported(MethodArgumentNotValidException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.BAD_REQUEST.value(), "Validation error"),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> exceptionMessageNoReadable(HttpMessageNotReadableException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.BAD_REQUEST.value(), "Validation error"),
				HttpStatus.BAD_REQUEST);
	}
	
}
