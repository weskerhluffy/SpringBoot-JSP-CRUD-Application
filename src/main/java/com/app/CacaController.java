package com.app;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.format.annotation.DateTimeFormat;

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
	private CorreoValidador emailValidator;

	private static final Logger logger = LoggerFactory.getLogger(CacaController.class);

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
		model.addObject("puta", "bitch");
		logger.info("me lleva la mierda");
		return model;
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView deleteUsers(@PathVariable long id) throws CacaException {
		if (id == 0) {
			throw new CacaException(id);
		}
		userService.deleteUser(id);
		return new ModelAndView("redirect:/caca");
	}

	// XXX:
	// https://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-parsing-date-and-time-information-from-a-request-parameter/
	// XXX:
	// https://www.javacodegeeks.com/2013/06/spring-mvc-validator-and-initbinder.html
	// XXX:
	// https://www.concretepage.com/spring/spring-mvc/spring-mvc-controlleradvice-annotation-example
	// XXX:
	// https://www.concretepage.com/spring/spring-mvc/spring-mvc-validator-with-initbinder-webdatabinder-registercustomeditor-example
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView userRegister(@ModelAttribute("usuario") @Valid User user, BindingResult result,
			@DateTimeFormat(pattern = "yyyy") Date fecha2, @DateTimeFormat(pattern = "yyyy-MM") Date fecha3)
			throws CacaException, FuckException {
		if (result.hasErrors()) {
			for (ObjectError erro : result.getAllErrors()) {
				System.out.println("error " + erro);
			}
			return new ModelAndView("redirect:/caca");
		}
		ModelAndView model = new ModelAndView("/ingresaCodigo");
		System.out.println("en usereg fecha " + fecha2);
		System.out.println("en usereg fecha 3 " + fecha3);
		if (user != null) {
			logger.info("puta madre " + user.getTelefono());
			if (user.getTelefono().equals("0")) {
				throw new CacaException(user.getId());
			}
			if (user.getTelefono().equals("1")) {
				throw new FuckException(user);
			}
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

	@InitBinder
	public void dataCaca(WebDataBinder binder) {
		System.out.println("bindeando mail val");
		binder.addValidators(emailValidator);
	}

	// XXX:
	// https://stackoverflow.com/questions/26487089/sessionattributes-and-responsebody-do-not-work-together
	@RequestMapping(value = "/mierda/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody User getMierda(@ModelAttribute("usuario") User usuario, @PathVariable String name,
			HttpSession sesion) {
		usuario.setName(name);
		return usuario;
	}

}
