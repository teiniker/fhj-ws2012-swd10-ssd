package at.fhj.swd.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import at.fhj.swd.selenium.pages.Pages;

public abstract class AbstractTest {

    protected WebDriver driver;
    protected Pages page;

    private String username = "TestNamen";
    private String password = "TestPasswort";

    @Before
    public void setUp() throws Exception {
	driver = new FirefoxDriver();
	page = new Pages(driver);
	page.maximizeWindow();
	page.openSite("http://localhost:8080/ExecNet/");
	page.loginPage().login(this.username, this.password);
    }

    /**
     * @return the username
     */
    private String getUsername() {
	return username;
    }

    /**
     * @param username
     *            the username to set
     */
    private void setUsername(String username) {
	this.username = username;
    }

    /**
     * @return the password
     */
    private String getPassword() {
	return password;
    }

    /**
     * @param password
     *            the password to set
     */
    private void setPassword(String password) {
	this.password = password;
    }

    @After
    public void tearDown() throws Exception {
	driver.close();
    }
}
