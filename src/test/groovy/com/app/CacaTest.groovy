package com.app


import javax.swing.Spring

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.web.WebAppConfiguration

import spock.lang.Specification


// XXX: http://www.baeldung.com/groovy-spock
// XXX: https://dzone.com/articles/using-spock-test-spring
// XXX: https://semaphoreci.com/community/tutorials/stubbing-and-mocking-in-java-with-the-spock-testing-framework
// XXX: https://www.mkyong.com/unittest/junit-spring-integration-example/
// XXX: https://dzone.com/articles/spring-integration-testing
// XXX: https://objectpartners.com/2017/04/18/spring-integration-testing-with-spock-mocks/
// XXX: https://www.petrikainulainen.net/programming/testing/writing-unit-tests-with-spock-framework-creating-a-maven-project/
// XXX: https://github.com/spockframework/spock/issues/421
// XXX: http://forum.spring.io/forum/spring-projects/container/113785-missing-servlet-context-in-spring-tests
@ContextConfiguration(
classes = [Application])
@TestPropertySource(properties = [
	"timezone = GMT", "logging.level.org.springframework: INFO"
])
@ActiveProfiles(profiles=["dev"])
@WebAppConfiguration
class CacaTest extends Specification{

	@Autowired
	IUserService userService;
	def "mierda"(){
		when:
		def token = userService.listAllUsers()
		then:
		token
	}
}
