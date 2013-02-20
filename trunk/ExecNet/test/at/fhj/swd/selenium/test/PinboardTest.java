package at.fhj.swd.selenium.test;

import org.junit.Test;
import org.testng.Assert;

import at.fhj.swd.selenium.AbstractTest;
import at.fhj.swd.selenium.pages.MySite;

public class PinboardTest extends AbstractTest {

    @Test
    public void insertPinBoardEntry() {
        MySite mySite = page.clickMySide();
        Assert.assertEquals("TEST", mySite.insertPinBoardEntry("TEST"));
    }

    @Test
    public void deletePinBoardEntry() {
        MySite mySite = page.clickMySide();
        mySite.deletePinBoardEntry();
    }

}
