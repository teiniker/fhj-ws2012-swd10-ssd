package at.fhj.swd.selenium.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import at.fhj.swd.selenium.AbstractTest;


public class LoginTest extends AbstractTest {


    @Test
    public void testIfLoginIsSuccessfullbyLogoutButton() {

        WebElement logoutButton = driver.findElement(By.id("j_idt23:j_idt54_itm"));
        assertTrue(logoutButton.isDisplayed());


    }

    @Test
    public void testIfLoginIsSuccessfullbyMainPageButton() {

        WebElement mainPageButton = driver.findElement(By.id("j_idt23:j_idt26_itm"));
        assertTrue(mainPageButton.isDisplayed());


    }

}
