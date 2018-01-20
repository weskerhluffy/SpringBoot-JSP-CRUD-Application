package com.app;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CorreoValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("if ur hart " + clazz);
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		System.out.println("validando correo " + user);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo", "", "correo is empty");
		if (!user.getCorreo().contains("@")) {
			errors.rejectValue("correo", "", "correo is not valid.");
			System.out.println("faio");
		}
	}

}
