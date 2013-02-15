package at.fhj.swd.selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import at.fhj.swd.selenium.AbstractPage;

public class Pages extends AbstractPage {

    public Pages(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }


    public void openSite(String site) {
        driver.get(site);
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void close() {
        driver.close();
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public Login loginPage() {
        return PageFactory.initElements(driver, Login.class);
    }

    public Homepage homepage() {
        return PageFactory.initElements(driver, Homepage.class);
    }

}
