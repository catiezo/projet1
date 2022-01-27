package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import Cucumber_Selenium.Cucumber_Selenium.NoConditionWait;


public class PageRechergeGoogle extends AbstractPage{
	
	public PageRechergeGoogle(WebDriver driver, ExtentTest testLog) {
		super(driver, testLog);
		// TODO Auto-generated constructor stub
	}


	
	public boolean verifElementExist() {

		 NoConditionWait.wait(driver, 6);
		
	WebElement elt=
		driver.findElement(By.xpath("//h3[contains(text(),\"EEEBrowser Automation\")]"));
 
	
	//WebElement elt= driver.findElement(By.xpath("//input[@name='q']"));
	
	return elt.isDisplayed();
	}


	@Override
	protected boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

}
