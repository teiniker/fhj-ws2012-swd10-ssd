package at.fhj.swd.selenium.test;

import org.junit.Test;

import at.fhj.swd.selenium.AbstractTest;


public class MySideTest extends AbstractTest {


    @Test
    public void openMySiteTest() {
        page.loginPage().login("TestName", "123456");
        page.clickMySide();

    }

}
