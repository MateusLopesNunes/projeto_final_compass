package com.br.compass.config.error;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.br.compass.dto.ExceptionResponseDto;

@RestControllerAdvice
public class ErrorHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> exceptionNoSuchElement(NoSuchElementException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> exceptionTypeMismatch(MethodArgumentTypeMismatchException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> exceptionResultDataAccess(EmptyResultDataAccessException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ConverterNotFoundException.class)
	public ResponseEntity<Object> exceptionConverterNotFound(ConverterNotFoundException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> exceptionMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.METHOD_NOT_ALLOWED.value(), exception.getMessage()),
				HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	/*@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> exceptionMethodNotSupported(MethodArgumentNotValidException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.BAD_REQUEST.value(), "Validation error"),
				HttpStatus.BAD_REQUEST);
	}*/
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> exceptionMessageNoReadable(HttpMessageNotReadableException exception) {
		return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.BAD_REQUEST.value(), "Validation error"),
				HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ExceptionResponseDto> handle(MethodArgumentNotValidException exception) {
		List<ExceptionResponseDto> dto = new ArrayList<>();
		List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
		
		fieldError.forEach(x -> {
			String messageError = messageSource.getMessage(x, LocaleContextHolder.getLocale());
			ExceptionResponseDto erro = new ExceptionResponseDto(HttpStatus.BAD_REQUEST.value(), "field " + x.getField() + " " + messageError);
			dto.add(erro);
		});
		
		return dto;
	}
	
}
