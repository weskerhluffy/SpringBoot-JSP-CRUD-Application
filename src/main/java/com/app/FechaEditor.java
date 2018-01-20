package com.app;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// XXX: http://keenformatics.blogspot.mx/2013/08/how-to-create-global-initbinder-in-spring.html
public class FechaEditor extends PropertyEditorSupport {

	public void setAsText(String value) {
		System.out.println("ill take your work " + value);
		SimpleDateFormat sea = new SimpleDateFormat("yyyy-MM");
		sea.setLenient(false);
		try {
			setValue(sea.parse(value));
			System.out.println("si se pudo");
		} catch (ParseException e) {
			setValue(null);
			System.out.println("no se pudo");
		}
	}

	public String getAsText() {
		String s = "";
		System.out.println("if ur heart should dry ");
		if (getValue() != null) {
			s = new SimpleDateFormat("yyyy-MM").format((Date) getValue());
		}
		return s;
	}
}
