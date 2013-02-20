package at.fhj.swd.selenium.test;

import org.junit.Test;

import at.fhj.swd.selenium.AbstractTest;
import at.fhj.swd.selenium.pages.Groups;

public class CommunityTest extends AbstractTest {

    @Test
    public void insertCommunity() {
        Groups groupSite = page.clickGroups();
        groupSite.insertCommunity("TEST-Community", "TEST-Description");
    }

}
