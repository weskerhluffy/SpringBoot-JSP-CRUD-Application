package com.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//@EnableWebMvc
@Configuration
public class ClientWebaConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		System.out.println("fuck");
		// XXX:
		// https://stackoverflow.com/questions/12059955/mapping-jsp-for-a-url-in-spring-3-without-using-controller
		registry.addViewController("login");
	}

	// XXX:
	// https://stackoverflow.com/questions/36831477/how-to-configure-spring-boot-mvc-app-for-jsp
	/*
	 * @Override public void configureViewResolvers(ViewResolverRegistry registry) {
	 * super.configureViewResolvers(registry); InternalResourceViewResolver bean =
	 * new InternalResourceViewResolver(); bean.setViewClass(JstlView.class);
	 * bean.setPrefix("/WEB-INF/jsp/"); bean.setSuffix(".jsp");
	 * registry.viewResolver(bean); System.out.println("fuck1"); }
	 * 
	 * @Bean public ViewResolver viewResolver() { InternalResourceViewResolver bean
	 * = new InternalResourceViewResolver();
	 * 
	 * bean.setViewClass(JstlView.class); bean.setPrefix("/WEB-INF/jsp/");
	 * bean.setSuffix(".jsp"); System.out.println("fuck2");
	 * 
	 * return bean; }
	 * 
	 */
}
