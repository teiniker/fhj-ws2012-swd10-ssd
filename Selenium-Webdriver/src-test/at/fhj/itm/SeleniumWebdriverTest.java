package at.fhj.itm;


import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;

public class SeleniumWebdriverTest{
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		//comment out for testing purposes -> to show exceptions
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//TODO exlicit wait vs. implicit
	}

	@Test
	public void testJunitGoogleWebdriver() throws Exception {

		String textMessage = "What is Selenium?";
		driver.get("http://www.google.at/");
		driver.findElement(By.id("gbqfq")).clear();
		driver.findElement(By.id("gbqfq")).sendKeys("selenium");
		driver.findElement(By.name("btnG")).click(); 
		driver.findElement(By.linkText("Selenium - Web Browser Automation")).click();
		String text = driver.findElement(By.id("mainContent")).getText();

		assertTrue(text.contains(textMessage)); 
	}

	@Test
	public void testJunitGoogleWebdriverXPath() throws Exception {
		String textMessage = "What is Selenium?";
		driver.get("http://www.google.at/");
		driver.findElement(By.xpath(".//*[@id='gbqfq']")).clear();
		driver.findElement(By.xpath(".//*[@id='gbqfq']")).sendKeys("selenium");//xpath: .//*[@id='gbqfb']
		driver.findElement(By.xpath(".//*[@id='rso']/li[1]/div/h3/a")).click(); 


		//absolute xpath
		String text = driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/h2[1]")).getText();
		// same with realtive xpath
		String text2 = driver.findElement(By.xpath(".//*[@id='mainContent']/h2[1]")).getText(); 	

		assertTrue(textMessage.equals(text));
		assertTrue(textMessage.equals(text2));
	}

	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
