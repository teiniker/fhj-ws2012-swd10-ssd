package at.fhj.swd.controller.selenium;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class test_01_Register {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void test01Reg() throws Exception {
        driver.get(baseUrl + "/ExecNet/new_login.jsf");
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("j_idt22:firstname")).clear();
        driver.findElement(By.id("j_idt22:firstname")).sendKeys("gerhard");
        driver.findElement(By.id("j_idt22:lastname")).clear();
        driver.findElement(By.id("j_idt22:lastname")).sendKeys("gerhard");
        driver.findElement(By.id("j_idt22:username")).clear();
        driver.findElement(By.id("j_idt22:username")).sendKeys("gerhard");
        driver.findElement(By.id("j_idt22:password")).clear();
        driver.findElement(By.id("j_idt22:password")).sendKeys("gerhard");
        driver.findElement(By.id("j_idt22:email")).clear();
        driver.findElement(By.id("j_idt22:email")).sendKeys("gerhard@aon.at");
        new Select(driver.findElement(By.id("j_idt22:culture"))).selectByVisibleText("Deutsch");
        driver.findElement(By.id("j_idt22:department")).clear();
        driver.findElement(By.id("j_idt22:department")).sendKeys("gerhard");
        driver.findElement(By.id("j_idt22:location")).clear();
        driver.findElement(By.id("j_idt22:location")).sendKeys("gerhard");
        driver.findElement(By.name("j_idt22:j_idt33")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alert.getText();
        } finally {
            acceptNextAlert = true;
        }
    }
}
