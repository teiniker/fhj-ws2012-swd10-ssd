package at.fhj.swd.selenium.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import at.fhj.swd.selenium.AbstractTest;

/**
 * @author Robert Kernbichler
 */
public class MySidePostingsTest extends AbstractTest {

    @Test
    public void addAndDeletePosting() {

	page.clickMySide();

	assertEquals(false, driver.getPageSource().contains("Robert war hier"));

	WebElement posting = driver.findElement(By.id("j_idt65:entry"));

	posting.sendKeys("Robert war hier");

	driver.findElement(By.name("j_idt65:j_idt69")).click();

	assertEquals(true, driver.getPageSource().contains("Robert war hier"));

	driver.findElement(
		By.xpath(".//*[@id='j_idt99']/table/tbody/tr/td[3]/a/img"))
		.click();

	assertEquals(false, driver.getPageSource().contains("Robert war hier"));

    }

}
