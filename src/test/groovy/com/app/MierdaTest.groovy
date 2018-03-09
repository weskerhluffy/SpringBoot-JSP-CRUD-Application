package com.app

import java.util.concurrent.TimeUnit

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxBinary
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.safari.SafariDriver

import io.github.bonigarcia.wdm.WebDriverManager
import spock.lang.Specification

// XXX: https://stackoverflow.com/questions/35031820/run-a-specific-test-in-a-single-test-class-with-spock-and-maven
class MierdaTest extends Specification {
	WebDriver driver

	def setup() {
		WebDriverManager.firefoxdriver().setup()

		/*
		 System.setProperty("webdriver.gecko.driver","/Users/ernesto/Downloads/geckodriver");
		 DesiredCapabilities capabilities=DesiredCapabilities.safari()
		 capabilities.setCapability("marionette", true);
		 driver = new FirefoxDriver(capabilities)
		 */
		//		System.setProperty("webdriver.gecko.driver",gecko);
		driver = new FirefoxDriver()
		driver.manage().timeouts().implicitlyWait 10, TimeUnit.SECONDS
	}

	def cleanup() {
		driver?.quit()
	}

	// XXX: http://timezra.blogspot.mx/2012/01/bdd-with-spock-and-selenium.html
	def "a user is greeted with shit"() {
		when:
		driver.get "https://www.google.com.mx"

		then:
		driver.title == "Google"
	}
}
