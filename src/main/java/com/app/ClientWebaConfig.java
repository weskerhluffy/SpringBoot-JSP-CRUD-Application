package com.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExceptionResolver;

@EnableWebMvc
@Configuration
public class ClientWebaConfig extends WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ClientWebaConfig.class);

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		logger.info("fucks");
		// XXX:
		// https://stackoverflow.com/questions/12059955/mapping-jsp-for-a-url-in-spring-3-without-using-controller
		registry.addViewController("login");
	}

	// XXX:
	// http://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/handler-adapter/
	// XXX: http://technicalstack.com/dispatcher-servlethandlermapping-controller/
	// XXX: http://www.baeldung.com/spring-mvc-handler-adapters
	// XXX:
	// http://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/handler-mapping/
	// XXX:
	// https://stackoverflow.com/questions/31893335/spring-mvc-autowire-requestmappinghandlermapping
	// XXX: http://www.programering.com/a/MDOzYzNwATM.html
	// XXX:
	// https://kalpacg.wordpress.com/2016/02/03/spring-mvc-dispatcherservlet-configuration-needs-to-include-a-handleradapter-that-supports-this-handler/
	@Bean(DispatcherServlet.HANDLER_MAPPING_BEAN_NAME)
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
		logger.info("puta mierda " + mapping.getDefaultHandler());

		return mapping;
	}

	@Bean(DispatcherServlet.HANDLER_ADAPTER_BEAN_NAME)
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter a = new RequestMappingHandlerAdapter();
		logger.info("cagada");
		return a;
	}

	// XXX:
	// http://www.logicbig.com/how-to/spring-mvc/spring-replacing-default-error-resolvers/
	// @Bean(DispatcherServlet.HANDLER_EXCEPTION_RESOLVER_BEAN_NAME)
	HandlerExceptionResolver customExceptionResolver(ApplicationContext ac) {

		logger.info("mierdaaa");
		ExceptionHandlerExceptionResolver e = new ExceptionHandlerExceptionResolver();
		e.setApplicationContext(ac);
		e.afterPropertiesSet();

		SimpleMappingExceptionResolver s = new SimpleMappingExceptionResolver();
		s.setDefaultErrorView("default-error");
		s.setDefaultStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

		HandlerExceptionResolverComposite c = new HandlerExceptionResolverComposite();
		c.setExceptionResolvers(Arrays.asList((HandlerExceptionResolver) e, s));
		return c;
	}

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/jsp/");
		bean.setSuffix(".jsp");
		return bean;
	}

	// XXX: https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
	// XXX:
	// https://www.javacodegeeks.com/2012/11/spring-mvc-error-handling-example.html
	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		CacaExceptionResolver caca = new CacaExceptionResolver();
		List<HandlerExceptionResolver> mierda = new ArrayList<HandlerExceptionResolver>();
		logger.info("pinche mierda");
		for (HandlerExceptionResolver handlerExceptionResolver : exceptionResolvers) {
			logger.info("assss " + handlerExceptionResolver);
			if (handlerExceptionResolver.getClass() != DefaultHandlerExceptionResolver.class) {
				mierda.add(handlerExceptionResolver);
			} else {
				mierda.add(caca);
			}
		}
		// exceptionResolvers.clear();
		// exceptionResolvers.addAll(Arrays.asList((HandlerExceptionResolver) caca));
		// exceptionResolvers.addAll(mierda);
		// exceptionResolvers.addAll(exceptionResolvers);
		exceptionResolvers.add(caca);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true);
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
	}

}
