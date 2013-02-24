package at.fhj.swd.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import at.fhj.swd.selenium.AbstractPage;

public class Settings extends AbstractPage {

    public Settings(WebDriver driver) {
	super(driver);
	// TODO Auto-generated constructor stub
    }

    private void setItem(String itemId, String itemValue) {

	WebElement item = driver.findElement(By.id(itemId));
	item.sendKeys(itemValue);

    }

    public void setNewSettings(String givenName, String lastName,
	    String password, String eMailAddress, String languageCode,
	    String departmentOrFunction, String whereYouCanBeFound) {

	setItem("j_idt68:firstname", givenName);
	setItem("j_idt68:lastname", lastName);
	setItem("j_idt68:password", password);
	setItem("j_idt68:email", eMailAddress);

	Select select = new Select(driver.findElement(By.id("j_idt68:culture")));
	// select.deselectAll();
	select.selectByValue(languageCode);

	setItem("j_idt68:department", departmentOrFunction);
	setItem("j_idt68:location", whereYouCanBeFound);

	driver.findElement(By.name("j_idt68:j_idt78")).click();
    }
    
    public String getPageHeadline(){
    	WebElement element = driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[2]/center/span"));
    	return element.getText();
    }


}
