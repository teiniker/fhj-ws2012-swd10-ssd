package at.fhj.swd.selenium.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import at.fhj.swd.selenium.AbstractTest;

/**
 * @author Michael Hausegger
 * 
 */
public class AdminTest extends AbstractTest {

    @Test
    public void isAdmin() {

	page.clickAdmin();

	WebElement button = driver.findElement(By
		.name("listingTableId:j_idt63:0:j_idt66"));

	// If the user is an admin this thest method raises no error.

    }

    @Test
    public void testPortalAdminSettable() {

	page.clickAdmin();

	WebElement buttonPortalAdminSet = driver.findElement(By
		.name("listingTableId:j_idt63:0:j_idt73"));

	buttonPortalAdminSet.click();

	WebElement buttonPortalAdminUnSet = driver.findElement(By
		.name("listingTableId:j_idt63:0:j_idt71"));

	buttonPortalAdminUnSet.click();

    }

    @Test
    public void testDeactivateActivateUser() {

	page.clickAdmin();

	WebElement buttonDeactivateUser = driver.findElement(By
		.name("listingTableId:j_idt63:0:j_idt76"));

	buttonDeactivateUser.click();

	WebElement buttonActivateUser = driver.findElement(By
		.name("listingTableId:j_idt63:0:j_idt78"));

	buttonActivateUser.click();

    }

}
