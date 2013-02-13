package at.fhj.swd.selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import at.fhj.swd.selenium.AbstractPage;

public class Login extends AbstractPage {

	@FindBy(xpath = "html/body/div/center/table/tbody/tr/td/form/table/tbody/tr[1]/td[2]/input")
    private WebElement userField;
    @FindBy(xpath = "html/body/div/center/table/tbody/tr/td/form/table/tbody/tr[2]/td[2]/input")
    private WebElement passwordField;

    @FindBy(xpath = "html/body/div/center/table/tbody/tr/td/form/input[2]")
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
