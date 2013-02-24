package at.fhj.swd.selenium.test;

import org.junit.Test;

import at.fhj.swd.selenium.AbstractTest;
import at.fhj.swd.selenium.pages.Homepage;


public class LogoutTest extends AbstractTest {

    Homepage homepage;

    @Test
    public void checkLogout() {

        homepage = new Homepage(driver);
        homepage.logout("Abmelden");


    }

}
