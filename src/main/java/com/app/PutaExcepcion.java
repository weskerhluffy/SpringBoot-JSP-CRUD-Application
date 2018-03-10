package com.app;

public class PutaExcepcion extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3825876531494333696L;

	public PutaExcepcion(String caca) {
		super("puta ex " + caca);
	}
}
