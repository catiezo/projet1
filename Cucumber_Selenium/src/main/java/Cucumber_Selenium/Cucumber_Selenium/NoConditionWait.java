package Cucumber_Selenium.Cucumber_Selenium;


import java.util.UUID;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NoConditionWait {
	
	private NoConditionWait() {}
	
	/**
	 * Mise en sommeil du Thread sans condition
	 * @param driver le Webdriver du test
	 * @param delay la durée d'attente en seconde
	 */
	public static void wait(WebDriver driver, int delay) {
		WebDriverWait tempWait = new WebDriverWait(driver, delay);
		try {
		    tempWait.until(ExpectedConditions.urlToBe(UUID.randomUUID().toString())); 
		}
		catch (TimeoutException e) {
			// empty on purpose
		}
	}
}

