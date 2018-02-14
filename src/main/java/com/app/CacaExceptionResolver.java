package com.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

// XXX: https://www.journaldev.com/2651/spring-mvc-exception-handling-controlleradvice-exceptionhandler-handlerexceptionresolver#handlerexceptionresolver
//@Component("simpleMappingExceptionResolver")
@Component(DispatcherServlet.HANDLER_EXCEPTION_RESOLVER_BEAN_NAME)
public class CacaExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(CacaController.class);

	// XXX: https://www.geeksforgeeks.org/instance-initialization-block-iib-java/
	// XXX: http://wiki.c2.com/?DoubleBraceInitialization
	// XXX: https://stackoverflow.com/questions/6802483/how-to-directly-initialize-a-hashmap-in-a-literal-way
	{
		setExceptionMappings(new Properties() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1642643382222560656L;

			{
				put("Exception", "generic_error.jsp");
			}
		});
		setDefaultErrorView("generic_error.jsp");
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String viewName = determineViewName(ex, request);
		logger.error("la concha de la lora :" + viewName);
		if (viewName != null) {
			Integer statusCode = super.determineStatusCode(request, viewName);
			if (statusCode != null) {
				applyStatusCodeIfPossible(request, response, statusCode);
			}
			request.setAttribute("error", ex.getMessage());
			return getModelAndView(viewName, ex, request);
		}
		return null;
	}
}