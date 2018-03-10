package com.app;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// XXX: https://dzone.com/articles/getting-jaegers-java-client-internal-metrics-into
@RestController
@RequestMapping("puta")
public class PutaController {
	User usuario = new User();

	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
	public User getAss(@PathVariable String name) throws PutaExcepcion {
		usuario.setName(name);
		if (name.equals("perra")) {
			throw new PutaExcepcion("q mamada");
		}

		return usuario;
	}

}
