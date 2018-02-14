package com.app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Employee Not Found")
public class CacaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1973223651591614168L;

	public CacaException(Long id) {
		super("No mames =" + id);
	}
}
