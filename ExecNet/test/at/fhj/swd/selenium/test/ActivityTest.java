package at.fhj.swd.selenium.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import at.fhj.swd.selenium.AbstractTest;

public class ActivityTest extends AbstractTest {

    @Test
    public void testIfAllDataOk() {
        page.homepage().fillActivity("TestString");
        Assert.assertTrue(page.homepage().getEntryField().getText().isEmpty());
    }

    @Test
    public void testIfDataIsNotEntered() {
        page.homepage().sendActivity();
        String testtext = driver.findElement(
            By.xpath("html/body/div[1]/table[3]/tbody/tr/td[2]/form/table/tbody/tr[6]/td/ul/li")).getText()
            .substring(8);
        Assert.assertEquals(testtext, ":entry: Überprüfungsfehler: Wert ist erforderlich.");

    }
}
