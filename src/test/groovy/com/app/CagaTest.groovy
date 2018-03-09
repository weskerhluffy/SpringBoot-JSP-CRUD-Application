package com.app

import java.util.concurrent.TimeUnit

import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.web.WebAppConfiguration

import io.github.bonigarcia.wdm.WebDriverManager
import spock.lang.Specification

class CagaTest extends Specification {
	WebDriver driver

	def setup() {
		new SpringApplicationBuilder(Application.class).initializers().run();
		Thread.sleep((Integer)1E4)
		WebDriverManager.firefoxdriver().setup()

		driver = new FirefoxDriver()
		driver.manage().timeouts().implicitlyWait 10, TimeUnit.SECONDS
	}

	def cleanup() {
		driver?.quit()
	}

	def "putada"(){

		when:
		driver.get "http://localhost:8080/login"

		then:
		driver.title == "fuckass"
	}
}
