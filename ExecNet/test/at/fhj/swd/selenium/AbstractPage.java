package at.fhj.swd.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import at.fhj.swd.selenium.pages.Admin;
import at.fhj.swd.selenium.pages.Documents;
import at.fhj.swd.selenium.pages.Groups;
import at.fhj.swd.selenium.pages.MySite;
import at.fhj.swd.selenium.pages.Settings;
import at.fhj.swd.selenium.pages.User;


public abstract class AbstractPage implements IPage {

    protected WebDriver driver;


    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }


    /* (non-Javadoc)
     * @see at.fhj.swd.selenium.IPage#clickMySide()
     */
    @Override
    public MySite clickMySide() {
        driver.findElement(By.id("j_idt23:j_idt30_itm")).findElement(By.tagName("a")).click();
        return new MySite(driver);
    }

    /* (non-Javadoc)
     * @see at.fhj.swd.selenium.IPage#clickUser()
     */
    @Override
    public User clickUser() {
        return new User(driver);
    }

    /* (non-Javadoc)
     * @see at.fhj.swd.selenium.IPage#clickGroups()
     */
    @Override
    public Groups clickGroups() {
        return new Groups(driver);
    }

    /* (non-Javadoc)
     * @see at.fhj.swd.selenium.IPage#clickDocuments()
     */
    @Override
    public Documents clickDocuments() {
        return new Documents(driver);
    }

    /* (non-Javadoc)
     * @see at.fhj.swd.selenium.IPage#clickSettings()
     */
    @Override
    public Settings clickSettings() {
        return new Settings(driver);
    }

    /* (non-Javadoc)
     * @see at.fhj.swd.selenium.IPage#clickAdmin()
     */
    @Override
    public Admin clickAdmin() {
        return new Admin(driver);
    }


}
