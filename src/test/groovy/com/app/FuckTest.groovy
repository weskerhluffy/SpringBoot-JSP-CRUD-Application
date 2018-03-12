package com.app

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.hamcrest.CoreMatchers.equalTo
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup

import javax.servlet.ServletContext

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockServletContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.util.UriComponentsBuilder

import spock.lang.Specification

// XXX: http://www.javarticles.com/2016/02/spring-webappconfiguration-annotation-example.html
// XXX: https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-mvc-unit-testing.html
@TestPropertySource(properties = [
	"timezone = GMT", "logging.level.org.springframework: INFO"
])
@ContextConfiguration(
classes = [Application])
@ActiveProfiles(profiles=["dev"])
@WebAppConfiguration
class FuckTest extends Specification {

	private static final Logger logger = LoggerFactory.getLogger(FuckTest.class);
	@Autowired
	protected WebApplicationContext wac

	@Autowired
	protected MockServletContext mockServletContext

	private MockMvc mockMvc

	def setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	def "grandes aventuras"(){
		when:
		ServletContext servletContext = wac.getServletContext();
		then:
		servletContext && servletContext instanceof MockServletContext

		for (String beanName : wac.getBeanDefinitionNames()) {
			if (beanName.contains("springCustomContextConfigurationExample")) {
				logger.info("putoBean Name: " + beanName);
				logger.info("Bean " + wac.getBean(beanName));
			}
		}
	}

	def "la puta mierda"(){
		ResultMatcher msg = MockMvcResultMatchers.model()
				.attribute("puta", "bitch")
		URI url = UriComponentsBuilder.fromUriString("/caca")
				.build().encode().toUri();
		logger.info("Call " + url + ", result: " +
				mockMvc.perform(get(url)).andReturn().getResponse().getContentAsString());

		when:
		ResultActions res=mockMvc.perform(get(url)).andExpect(status().isOk())
				.andExpect(msg);
		then:
		res.andReturn()
	}

	// XXX: https://blog.trifork.com/2012/12/11/properly-testing-spring-mvc-controllers/
	def "restacaca"(){
		when:
		ResultActions res=mockMvc.perform(get("/puta/gata")).andExpect(status().isAccepted()).andExpect(jsonPath("\$.nombre", equalTo("gata")))
		then:
		res.andReturn()
	}
}
