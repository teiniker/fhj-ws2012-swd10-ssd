package at.fhj.itm.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Google page according page object pattern.
 * @author CKRENN3
 *
 */
public class GooglePage extends PageObjectBase {

	
	/**
	 * Constructor taking web driver as parameter.
	 * @param driver the web driver.
	 */
	public GooglePage(WebDriver driver) {
		super(driver,"Google");
	}
	
	/**
	 * Searches Google for Selenium and loads Selenium page.
	 * @return The Selenium page. 
	 *
	 */
	public SeleniumPage searchForSeleniumPage()  {
		
		driver.findElement(By.xpath(".//*[@id='gbqfq']")).clear();
		driver.findElement(By.xpath(".//*[@id='gbqfq']")).sendKeys("selenium");
		driver.findElement(By.xpath(".//*[@id='rso']/li[1]/div/h3/a")).click(); 

		
		
		// note: click does not wait for page to load, so wait
		WaitForNewPageToLoad(PageObjectBase.DEFAULT_TIMEOUT_SECONDS);	
		
		return new SeleniumPage(driver);
	}
	
	
	
	
}
