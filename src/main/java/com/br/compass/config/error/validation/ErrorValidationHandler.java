package com.br.compass.config.error.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.compass.dto.ErrorFormDto;

@RestControllerAdvice
public class ErrorValidationHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorFormDto> handle(MethodArgumentNotValidException exception) {
		List<ErrorFormDto> dto = new ArrayList<>();
		List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
		
		fieldError.forEach(x -> {
			String messageError = messageSource.getMessage(x, LocaleContextHolder.getLocale());
			ErrorFormDto erro = new ErrorFormDto(x.getField() , messageError);
			dto.add(erro);
		});
		
		return dto;
	}
}
