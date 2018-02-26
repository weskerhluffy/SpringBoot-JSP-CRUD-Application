package com.app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Puto")
public class FuckException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1973223651591614168L;

	public FuckException(User cac) {
		super("No mames fuck =" + cac);
	}
}
