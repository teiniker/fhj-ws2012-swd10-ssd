package at.fhj.swd.selenium.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;

import at.fhj.swd.selenium.pages.Pages;



public class LoginTest {

    WebDriver driver;
    Pages page;
    
    
    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        page = new Pages(driver);
        page.maximizeWindow();
        page.openSite("http://localhost:8080/ExecNet/");
       
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIfLoginIsSuccessfullbyLogoutButton() {
        
        page.loginPage().login("TestName", "123456");
        WebElement logoutButton = driver.findElement(By.id("j_idt23:j_idt54_itm"));
        assertTrue(logoutButton.isDisplayed());
        
        
    }
    @Test
    public void testIfLoginIsSuccessfullbyMainPageButton() {
        
        page.loginPage().login("TestName", "123456");
        WebElement mainPageButton = driver.findElement(By.id("j_idt23:j_idt26_itm"));
        assertTrue(mainPageButton.isDisplayed());
        
        
    }

}
