package at.fhj.swd.selenium.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import at.fhj.swd.selenium.AbstractTest;

/**
 * @author Robert Kernbichler
 */
public class LanguageSwitchTest extends AbstractTest {

    @Test
    public void testGerman() {

	page.clickDocuments();

	WebElement deImg = driver.findElement(By
		.xpath(".//*[@id='j_idt11']/table/tbody/tr/td[1]/a/img"));

	deImg.click();

	WebElement docText = driver.findElement(By
		.xpath(".//*[@id='j_idt69']/center/span"));

	WebElement searchButton = driver.findElement(By
		.className("rf-fu-btn-cnt-add"));

	assertEquals(searchButton.getText(), "Durchsuchen");

	assertEquals(docText.getText(), "Dokumente");

    }

    @Test
    public void testEnglisch() {

	page.clickDocuments();

	WebElement deImg = driver.findElement(By
		.xpath(".//*[@id='j_idt11']/table/tbody/tr/td[2]/a/img"));

	deImg.click();

	WebElement docText = driver.findElement(By
		.xpath(".//*[@id='j_idt69']/center/span"));

	WebElement searchButton = driver.findElement(By
		.className("rf-fu-btn-cnt-add"));

	assertEquals(searchButton.getText(), "browse");

	assertEquals(docText.getText(), "Documents");

    }

}
