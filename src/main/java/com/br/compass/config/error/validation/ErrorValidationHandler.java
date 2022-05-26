package com.br.compass.config.error.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.compass.dto.ErrorFormDto;

@RestControllerAdvice
public class ErrorValidationHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(Exception.class)
	public List<ErrorFormDto> handleNotFound(Exception exception) {
		List<ErrorFormDto> dto = new ArrayList<>();
		ErrorFormDto erro = new ErrorFormDto(HttpStatus.NOT_FOUND.value() , exception.getMessage());
		dto.add(erro);
		
		return dto;
	}
	
}
