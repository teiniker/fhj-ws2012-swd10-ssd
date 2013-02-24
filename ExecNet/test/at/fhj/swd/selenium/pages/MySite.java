package at.fhj.swd.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import at.fhj.swd.selenium.AbstractPage;

public class MySite extends AbstractPage {

    public MySite(WebDriver driver) {
        super(driver);
    }

    public String insertPinBoardEntry(String entry) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td/form/table/tbody/tr/td[2]/input")).sendKeys(entry);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td/form/input[2]")).click();
        
        WebElement element = driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[2]/form/table/tbody/tr/td[2]/span"));
        return element.getText();
    }

    public void deletePinBoardEntry() {
        WebElement element = driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[2]/form/table/tbody/tr/td[3]/a"));
        element.click();
    }

    public String getPageHeadline(){
    	WebElement element = driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[2]/center/span"));
    	return element.getText();
    }
}
