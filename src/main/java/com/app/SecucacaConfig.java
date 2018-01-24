package com.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//XXX: http://www.baeldung.com/spring-security-with-maven
//XXX: http://www.baeldung.com/spring-mvc-tutorial#configviews
//XXX: http://www.baeldung.com/spring-security-login
//XXX: https://www.javabullets.com/auto-generating-spring-security-accessing-the-in-memory-database/
@Configuration
@ImportResource({ "classpath:webSecurityConfig.xml" })
public class SecucacaConfig {
	public SecucacaConfig() {
		super();
	}
}
