package at.fhj.swd.selenium.test;

import org.junit.Test;

import at.fhj.swd.selenium.AbstractTest;
import at.fhj.swd.selenium.pages.Settings;

/**
 * @author Michael Hausegger
 * 
 */
public class SettingsTest extends AbstractTest {

    private String oldPassword = null;

    private static final String newPassword = "joJaSo123454";

    @Test
    public void testChangePasswordViaSettings() throws Exception {

	Settings settingsPage = page.clickSettings();

	this.oldPassword = this.getPassword();

	settingsPage.setNewSettings("Hansi", "de-Vogel", newPassword,
		"mojo@mojogras.com", "de", "departmentOrFunction",
		"whereYouCanBeFound");

	Settings settingsPage2 = page.clickSettings();

	settingsPage.setNewSettings("Hansi", "de-Vogel", this.oldPassword,
		"mojo@mojogras.com", "de", "departmentOrFunction",
		"whereYouCanBeFound");

    }

}
