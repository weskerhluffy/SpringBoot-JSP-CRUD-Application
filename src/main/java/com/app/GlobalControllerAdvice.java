package com.app;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(CacaController.class);

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		System.out.println("registrando el validadodr date");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fecha1", new CustomDateEditor(dateFormat, true));
		// binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,
		// true));
		// binder.registerCustomEditor(Date.class, "fecha3",new FechaEditor());
	}

	@ModelAttribute
	public void globalAttributes(Model model) {
		model.addAttribute("msg", "Welcome to ASS!");
	}

	// org.springframework.core.convert.ConversionFailedException
	@ExceptionHandler(FileNotFoundException.class)
	public ModelAndView myError(Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.setViewName("error");
		return mav;
	}

	// XXX: https://dzone.com/articles/global-exception-handling-with-controlleradvice
	@ExceptionHandler(CacaException.class)
	public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex) {
		logger.error("Requested URL=" + request.getRequestURL());
		logger.error("Exception Raised=" + ex);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", request.getRequestURL());

		modelAndView.setViewName("error");
		return modelAndView;
	}
}
