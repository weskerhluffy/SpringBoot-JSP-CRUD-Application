package com.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author Fatih Totrakanlı
 *
 */

@ImportResource({ "classpath:spring-database.xml" })
@ComponentScan
// XXX:
// https://stackoverflow.com/questions/29251980/spring-boot-with-authentication-login-page-not-found-404
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class, WebMvcAutoConfiguration.class })
public class Application extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(ConfiguraWebaExpcetion.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.logStartupInfo(true);
		return application.sources(ClientWebaConfig.class, ConfiguraWebaExpcetion.class, DataCacaConfig.class);
	}

	// XXX:
	// https://stackoverflow.com/questions/22389996/how-to-configure-spring-boot-servlet-like-in-web-xml
	@Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
	public DispatcherServlet dispatcherServlet(WebApplicationContext wac) {
		DispatcherServlet ds = new DispatcherServlet(wac);
		// ds.setDetectAllHandlerAdapters(false);
		// ds.setDetectAllHandlerMappings(false);
		ds.setDetectAllHandlerExceptionResolvers(false);
		return ds;
	}

	// XXX:
	// https://www.leveluplunch.com/blog/2014/04/01/spring-boot-configure-servlet-mapping-filters/
	// XXX: https://github.com/spring-projects/spring-boot/issues/91
	@Bean
	public ServletRegistrationBean dispatcherServletRegistration(WebApplicationContext wac) {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(wac));
		logger.info("patoavent");
		registration.addUrlMappings("/");
		return registration;
	}

	// XXX: https://dzone.com/articles/spring-1
	// XXX:
	// https://www.intertech.com/Blog/how-to-use-springs-webapplicationinitializer/
	// XXX: http://www.baeldung.com/spring-xml-vs-java-config
	public static void main(String[] args) {
		// SpringApplication.run(Application.class, args);
		new SpringApplicationBuilder(Application.class).initializers().run(args);
	}

}
