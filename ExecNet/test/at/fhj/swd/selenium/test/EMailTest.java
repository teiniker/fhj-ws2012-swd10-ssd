package at.fhj.swd.selenium.test;

import org.junit.Test;
import org.testng.Assert;

import at.fhj.swd.selenium.AbstractTest;
import at.fhj.swd.selenium.pages.User;

public class EMailTest extends AbstractTest {

	@Test
	public void sendEMail(){
		User userpage = super.page.clickUser();
		String result = userpage.SendEmail("eibegger@gmail.com", "Send E-Mail Test", "Selenium ist super!");
		Assert.assertTrue(result.contains("successful"));
	}
	
}
