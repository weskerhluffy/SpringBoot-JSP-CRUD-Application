package com.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ConfiguraWebaExpcetion extends AbstractAnnotationConfigDispatcherServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(ConfiguraWebaExpcetion.class);

	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.info("q verga");
		assert false;
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		assert false;
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		assert false;
		return new String[] { "/" };
	}

	// XXX: http://www.logicbig.com/how-to/spring-mvc/spring-replacing-default-error-resolvers/
	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext wac) {
		DispatcherServlet ds = new DispatcherServlet(wac);
		assert false;
		logger.info("vergas legendarias");
		ds.setDetectAllHandlerExceptionResolvers(false);
		return ds;
	}

}
