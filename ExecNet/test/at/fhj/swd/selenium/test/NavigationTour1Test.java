package at.fhj.swd.selenium.test;

import org.junit.Test;
import org.testng.Assert;

import at.fhj.swd.selenium.AbstractTest;
import at.fhj.swd.selenium.pages.Documents;
import at.fhj.swd.selenium.pages.Groups;
import at.fhj.swd.selenium.pages.Login;
import at.fhj.swd.selenium.pages.MySite;
import at.fhj.swd.selenium.pages.Settings;

public class NavigationTour1Test extends AbstractTest {

	@Test
	public void navigateTour1(){
		
		MySite mySite = super.page.homepage().clickMySide();
		String headlineMySite = mySite.getPageHeadline();
		Assert.assertEquals("Meine Seite", headlineMySite);
		
		Groups groups = mySite.clickGroups();
		String headlineGroups = groups.getPageHeadline();
		Assert.assertEquals("Gruppen", headlineGroups);
		
		Settings settings = groups.clickSettings();
		String headlineSettings = settings.getPageHeadline();
		Assert.assertEquals("Einstellungen", headlineSettings);
		
	}

}
