package at.fhj.swd.selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import at.fhj.swd.selenium.AbstractPage;

public class Login extends AbstractPage {


    @FindBy(id = "j_idt22:username")
    private WebElement userField;
    @FindBy(id = "j_idt22:password")
    private WebElement passwordField;

    @FindBy(name = "j_idt22:j_idt27")
    private WebElement loginButton;

    public Login(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    public Homepage login(String username, String password) {
        userField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return new Homepage(driver);
    }


}
