package at.fhj.swd.view;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Documents {

    private WebDriver driver;
    private String baseUrl = "http://localhost:8080/ExecNet";
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        login();

    }

    private void login() throws Exception {
        String expectedURL = "http://localhost:8080/ExecNet/new_login.xhtml";
        driver.get("http://localhost:8080/ExecNet/");
        driver.findElement(By.xpath("//td[2]/input")).clear();
        driver.findElement(By.xpath("//td[2]/input")).sendKeys("moserj");
        driver.findElement(By.xpath("//tr[2]/td[2]/input")).clear();
        driver.findElement(By.xpath("//tr[2]/td[2]/input")).sendKeys("asdfghjkl");
        driver.findElement(By.xpath("//input[2]")).click();
        Assert.assertEquals(expectedURL, driver.getCurrentUrl().substring(0, expectedURL.length()));
    }


    @Test
    public void testAddDokument() throws Exception {
        driver.findElement(By.linkText("Dokumente")).click();
        driver.findElement(By.xpath("//div/table/tbody/tr/td/div/div")).click();
        driver.findElement(By.xpath("//tr[2]/td/div/div")).click();
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(
            "C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg");
        Thread.sleep(500);
        driver.findElement(By.xpath("//span[2]/span")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//div/table/tbody/tr/td/div/div")).click();
        driver.findElement(By.xpath("//tr[2]/td/div/div")).click();
    }

    @Test
    public void testDeleteDokument() throws Exception {
        driver.findElement(By.linkText("Dokumente")).click();
        // driver.findElement(By.cssSelector("a[title=\"Dokument löschen\"] > img")).click();
        driver.findElement(By.xpath("//td[5]/a/img")).click();;
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
}
