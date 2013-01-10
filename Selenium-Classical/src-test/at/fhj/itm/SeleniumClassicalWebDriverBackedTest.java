package at.fhj.itm;


import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Classical Example - needs Selenium Server
 * @author CKRENN3
 *
 */

public class SeleniumClassicalWebDriverBackedTest
{
    private Selenium selenium;

    static Object startupSync = new Object();

    
    @Before
    public void setUp()
    {
    	String baseUrl = "https://www.google.at/";
    	//startSelenium(baseUrl);
    	WebDriver  driver = new FirefoxDriver();
    	
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
    }

    /**
     * Workaround to fix error: Unable to bind to locking port 7054 within 45000 ms
     * @param baseUrl
     */
    public void startSelenium(String baseUrl) {
        synchronized(startupSync) {
        	WebDriver  driver = new FirefoxDriver();
        	
    		selenium = new WebDriverBackedSelenium(driver, baseUrl);
        }

    }
    @After
    public void tearDown()
    {
        selenium.stop();
    }
    
    
    @Test
    public void testGoogle()
    {
    
    	selenium.open("/");
    	selenium.waitForPageToLoad("30000");
    	selenium.type("id=gbqfq", "selenium");
    	selenium.waitForPageToLoad("30000");
    	selenium.click("id=gbqfb");
        selenium.waitForPageToLoad("30000");
    	selenium.click("link=Selenium - Web Browser Automation");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("What is Selenium?"));
    	/*
    	selenium.open("/");
        selenium.waitForPageToLoad("30000");
        selenium.type("q", "selenium");
        selenium.waitForPageToLoad("30000");
        selenium.click("btnG");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Selenium - Web Browser Automation");
        selenium.waitForPageToLoad("30000");
        assertTrue(selenium.isTextPresent("What is Selenium?"));        
        selenium.close();
        */
    }
}
