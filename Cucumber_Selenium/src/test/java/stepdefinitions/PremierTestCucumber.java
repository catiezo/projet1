package stepdefinitions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Cucumber_Selenium.Cucumber_Selenium.HtmlReporterUtils;
import Cucumber_Selenium.Cucumber_Selenium.NoConditionWait;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import page.PageRechergeGoogle;

public class PremierTestCucumber {
	
	 public static WebDriver driver;
	 
	 private static final Date date	= new Date();
		private static final String START_DATE_TECH	= new SimpleDateFormat("yyMMddHHmmss").format(date);
		//private static final String START_DATE_FONC	= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
		private static final String reportLocation = System.getProperty("user.dir") + "\\reports\\rapport_FIRSTTEST_" + START_DATE_TECH + ".html";
		
		private static final ExtentReports report = HtmlReporterUtils.setupReport(reportLocation, "TNR FIRSTTEST", "TNR FIRSTTEST");
		private static final ExtentTest testReport	= report.createTest("mock");
		private static final ExtentTest testLog		= testReport.createNode("mock");
	
    @Given("user is on homepage")
	public void user_is_on_homepage() {
    	
    	System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\selenium_data\\geckodriver.exe");
    	driver= new FirefoxDriver();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	driver.get("https://www.google.fr/");
    	driver.findElement(By.xpath("//div[contains(text(),\"J'accepte\")]")).click();
		System.out.println("user is on homepage");
		testLog.info("user is on homepage");
	   
	}

	@When("user navigates to Login Page")
	public void user_navigates_to_Login_Page() {
		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("cucumber selenium java");
		System.out.println("user navigates to Login Page");
		testLog.info("user navigates to Login Page");
		
	}

	@When("user enters username and Password")
	public void user_enters_username_and_Password() {
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
		System.out.println("user enters username and Password");
		testLog.info("user enters username and Password");
	}

	@Then("success message is displayed")
	public void success_message_is_displayed() {	
		
	    try {
		Assertions.assertTrue(new PageRechergeGoogle(driver, testLog).verifElementExist());
		System.out.println("Dans la liste, les données renvoyées contiennent Cuccumber documentation");
		testLog.pass("Dans la liste, les données renvoyées contiennent Cuccumber documentation");
        
	    } catch (AssertionError assertErr) {
        	
        	System.out.println("erreur ");
        	testLog.fail("erreur");
		
		
	    } catch (Exception e) {
	    	System.out.println("Exeption");
	    	testLog.error("exeption");
		throw e;
	    }	
	
	    
	}

	/////////////////////////le rapport à la fin de chaque scénario.
	
	
	@After
	public void afterEachScenario() {
		NoConditionWait.wait(driver, 3);	
		if (testLog !=null) {
			report.flush();
		}
		
		driver.quit();
	}
	

}
