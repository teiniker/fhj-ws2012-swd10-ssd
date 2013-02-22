package at.fhj.swd.selenium.pages;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.selenesedriver.SendKeys;
import org.openqa.selenium.support.FindBy;


public class News {

    WebDriver driver;
    
    
    public News(WebDriver driver) {
        super();
        this.driver = driver;
    }
    
    public void insertNews(String title, String text) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("j_idt64:title")).sendKeys(title);
        driver.findElement(By.id("j_idt64:entry")).sendKeys(text);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[2]/form/div/div/table/tbody/tr[3]/td[2]/input")).click();
        
        WebElement addNewsLink = driver.findElement(By.linkText("Add News"));
        assertTrue(addNewsLink.isDisplayed());
        
    }
    
}
