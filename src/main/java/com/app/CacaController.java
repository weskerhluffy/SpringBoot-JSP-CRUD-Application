package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.context.annotation.Scope;

/**
 * @author Fatih Totrakanlı
 *
 */

@Controller
@Scope("session")
@RequestMapping("/caca")
public class CacaController {

	private IUserService userService;
	private User usuarioTmp;

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView caca() {
		ModelAndView model = new ModelAndView("caca");
		model.addObject("list", userService.listAllUsers());
		System.out.println("me lleva la mierda");
		return model;
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView deleteUsers(@PathVariable long id) {
		userService.deleteUser(id);
		return new ModelAndView("redirect:/caca");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView userRegister(@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView("caca");
		if (user != null) {
			// TODO: Validar diccionario de datos
			// userService.saveUser(user);
			if (userService.enviarCodigo(user)) {
				model.addObject("warning", "Codigo enviado correctamente");
				usuarioTmp = user;
				System.out.println("generado tel " + usuarioTmp.getTelefono());

			} else {
				model.addObject("danger", "Telefono incorrecto");
			}

		} else {
			model.addObject("danger", "Telefono incorrecto");
		}
		return new ModelAndView("redirect:/caca/ingresaCodigo");
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("edit");
		User user = userService.getUserById(id);
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "/ingresaCodigo", method = RequestMethod.GET)
	public ModelAndView ingresaCodigo() {
		System.out.println("q onda " + usuarioTmp);
		ModelAndView model = new ModelAndView("ingresaCodigo");
		model.addObject("user", usuarioTmp);
		return model;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("id") long id, @RequestParam("name") String name,
			@RequestParam("surname") String surname, @RequestParam("adress") String adress) {
		User user = userService.getUserById(id);
		user.setName(name);
		user.setSurname(surname);
		user.setAdress(adress);
		userService.saveUser(user);
		return new ModelAndView("redirect:/caca");
	}

}
