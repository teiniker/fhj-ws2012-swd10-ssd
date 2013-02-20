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
	page.loginPage().login(this.getUsername(), this.getPassword());
    }

    /**
     * @return the username
     */
    public String getUsername() {
	return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
	this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }

    @After
    public void tearDown() throws Exception {
	driver.close();
    }
}
