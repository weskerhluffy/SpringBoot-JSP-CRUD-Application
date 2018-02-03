package com.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneraCaca {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String password = "das";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode(password));
	}

}