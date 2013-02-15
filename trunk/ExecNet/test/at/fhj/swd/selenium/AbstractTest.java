package at.fhj.swd.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import at.fhj.swd.selenium.pages.Pages;


public abstract class AbstractTest {

    protected WebDriver driver;
    protected Pages page;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        page = new Pages(driver);
        page.maximizeWindow();
        page.openSite("http://localhost:8080/ExecNet/");
        page.loginPage().login("TestNamen", "TestPasswort");
    }

    @After
    public void tearDown() throws Exception {
        // driver.close();
    }
}
