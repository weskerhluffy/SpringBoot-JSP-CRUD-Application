package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.context.annotation.Scope;

/**
 * @author Fatih Totrakanlı
 *
 */

// XXX: https://dzone.com/articles/using-http-session-spring
// XXX:
// https://stackoverflow.com/questions/18791645/how-to-use-session-attributes-in-spring-mvc
// XXX:
// https://www.intertech.com/Blog/understanding-spring-mvc-model-and-session-attributes/
// XXX: http://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation
@Controller
@SessionAttributes("usuario")
@RequestMapping("/caca")
public class CacaController {

	private IUserService userService;

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@ModelAttribute("usuario")
	public User generaMierda() {
		return new User();
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
	public ModelAndView userRegister(@ModelAttribute("usuario") User user) {
		ModelAndView model = new ModelAndView("/ingresaCodigo");
		System.out.println("en usereg");
		if (user != null) {
			// TODO: Validar diccionario de datos
			// userService.saveUser(user);
			if (userService.enviarCodigo(user)) {
				model.addObject("warning", "Codigo enviado correctamente");
				assert user.getAuthenticated();
				System.out.println("usuario maldito " + user);

			} else {
				model.addObject("danger", "Telefono incorrecto");
			}

		} else {
			model.addObject("danger", "Telefono incorrecto");
		}
		return model;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("edit");
		User user = userService.getUserById(id);
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "/ingresaCodigoo", method = RequestMethod.POST)
	public ModelAndView ingresaCodigoo(@ModelAttribute("usuario") User usuario, @RequestParam("shit") Integer caca) {
		System.out.println("q ondaaaa " + usuario + " la cagada es " + caca);
		ModelAndView model = null;
		if (caca.equals(usuario.getUserId())) {
			System.out.println("los nums coinciden");
			model = new ModelAndView("edit");
		} else {
			System.out.println("los nums NO coinciden");
			model = new ModelAndView("ingresaCodigo");
			model.addObject("danger", "dr zaias");
		}
		// model.addObject("user", usuario);
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
