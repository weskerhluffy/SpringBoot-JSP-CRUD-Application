import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

// XXX: https://www.ibm.com/support/knowledgecenter/en/SSYKE2_7.1.0/com.ibm.java.security.component.71.doc/security-component/jsse2Docs/systemprops.html
// XXX: https://github.com/SeleniumHQ/selenium/issues/3880
// XXX: http://toolsqa.com/selenium-webdriver/how-to-use-geckodriver/
public class AssTest {
	public static void main(String[] args) {
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		firefoxBinary.addCommandLineOptions("--headless");
		System.setProperty("webdriver.gecko.driver", "/Users/ernesto/workspace_weba/valida_kue/src/pruebas/geckodriver");
		FirefoxDriver driver = new FirefoxDriver();
		try {
			driver.get("http://www.google.com");
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			WebElement queryBox = driver.findElement(By.name("q"));
			queryBox.sendKeys("headless firefox");
			WebElement searchBtn = driver.findElement(By.name("btnK"));
			searchBtn.click();
			WebElement iresDiv = driver.findElement(By.id("ires"));
			iresDiv.findElements(By.tagName("a")).get(0).click();
			System.out.println(driver.getPageSource());
		} finally {
			driver.quit();
		}
	}
}