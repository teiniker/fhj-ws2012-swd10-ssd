package at.fhj.itm.tests;

import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import at.fhj.itm.pageobjects.GooglePage;
import at.fhj.itm.pageobjects.SeleniumPage;

import java.util.concurrent.TimeUnit;

/**
 * Test example showing how to implement Page object pattern with selenium.
 * @author CKRENN3
 *
 */
public class SeleniumWebdriverPagePatternTest{
	
	/**
	 * Webdriver object	
	 */
	private WebDriver driver;
	
	/**
	 * Test setup method.
	 * @throws Exception in case of errors
	 */
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		//comment out for testing purposes -> to show exceptions
		//set timeout for handling element load
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://www.google.at/");
	}

	@Test
	/**
	 * Searches Google for Selenium and loads selenium home page.
	 * 
	 * Bad test example: exposes internals - non functional
	 * 
	 * @throws Exception in case of errors
	 */
	public void testSearchSeleniumPageWithGoogleAndValidateSeleniumPageBadExample() throws Exception {
		
		GooglePage googlePage = new GooglePage(driver);
		SeleniumPage seleniumPage = googlePage.searchForSeleniumPage();
		
		String textMessage = "What is Selenium?";
	
		//the ugly part.
		String text = seleniumPage.GetTextOfxPathElement(".//*[@id='mainContent']/h2[1]");
		
		assertTrue(textMessage.equals(text));
	}
	
	@Test
	/**
	 * 
	 * Searches Google for Selenium and loads selenium home page.
	 * 
	 * better test example: does not expose internal - functional but exposes internals
	 * @throws Exception
	 */
	public void testSearchSeleniumPageWithGoogleAndValidateSeleniumPageBetterExample() throws Exception {
		
		GooglePage googlePage = new GooglePage(driver);
		SeleniumPage seleniumPage = googlePage.searchForSeleniumPage();
		
		//better: define functional string, but what if selenium page content changes? change every test?
		String textMessage = "What is Selenium?";
		assertTrue(seleniumPage.IsMessagePresent(textMessage));
	}
	
	@Test
	/**
	 * Searches Google for Selenium and loads Selenium home page.
	 * 
	 * Good test example - fully functional - page pattern optimized
	 * 
	 * 	@throws Exception in case of errors
	 */
	public void testSearchSeleniumPageWithGoogleAndValidateSeleniumPage() throws Exception {
		
		GooglePage googlePage = new GooglePage(driver);
		SeleniumPage seleniumPage = googlePage.searchForSeleniumPage();
		
		assertTrue(seleniumPage.IsWelcomeMessagePresent());
	}
	
	
	/**
	 * Tear down.
	 * @throws Exception in case of errors
	 */
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
