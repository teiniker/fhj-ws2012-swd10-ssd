package at.fhj.swd.selenium.test;

import org.junit.Test;
import org.testng.Assert;

import at.fhj.swd.selenium.AbstractTest;
import at.fhj.swd.selenium.pages.User;

public class SearchUserTest extends AbstractTest {

    @Test
    public void SearchUser() {
        User user = page.clickUserOverview();
        Assert.assertEquals("TestNamen", user.SearchUser("TestNamen"));
    }

}
