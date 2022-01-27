package page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

/**
 * Mère de toutes les pages au sens Page Object Model.
 * Permets de simplement avoir une seule et unique façon de transmettre
 * le {@link org.openqa.selenium.WebDriver}.
 */
public abstract class AbstractPage {

    /**
     * Driver permettant d'interagir avec les objets reels des pages webs (btn, champs, liens, etc...)
     */
    protected final WebDriver driver;
    protected final ExtentTest testLog;
    
    private static final int TIMEOUT_LIMIT	= 10;
    protected WebDriverWait timeout;

    /**
     * Contructeur
     * @param driver le WebDriver
     * @param testLog le TestLog (ExtentTest)
     * @param timeout le timeout (WebDriverWait)
     */
    protected AbstractPage(WebDriver driver, ExtentTest testLog, WebDriverWait timeout) {
        this.driver		= driver;
        this.testLog	= testLog;
        this.timeout	= timeout;
    }
    
    /**
     * Constructeur
     * Le WebDriverWait est par défaut à 10s
     * @param driver WebDriver
     * @param testLog ExtentTest
     * 
     */
    protected AbstractPage(WebDriver driver, ExtentTest testLog) {
        this.driver		= driver;
        this.testLog	= testLog;
        this.timeout	= new WebDriverWait(driver, TIMEOUT_LIMIT);
    }
    /**
     * Constructeur
     * Le WebDriverWait est par défaut à 10s
     * @param driver WebDriver
     * 
     */
    protected AbstractPage(WebDriver driver) {
        this.driver		= driver;
        this.testLog	= null;
        this.timeout	= new WebDriverWait(driver, TIMEOUT_LIMIT);
    }
    /**
     * Constructeur
     * @param driver WebDriver
     * @param timeout WebDriverWait
     * 
     */
    protected AbstractPage(WebDriver driver, WebDriverWait timeout) {
        this.driver		= driver;
        this.testLog	= null;
        this.timeout	= timeout;
    }
    
    /**
     * Test de l'affichage de la page
     * @return true si la page est affichée ; false sinon
     */
    protected abstract boolean exists(); 
}