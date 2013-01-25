package at.fhj.swd.selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {

       private WebDriver driver;

       @FindBy(id = "")
       private WebElement userField;
       @FindBy(id = "")
       private WebElement passwordField;

       @FindBy(id = "")
       private WebElement loginButton;
       @FindBy(id = "")
       private WebElement logoutButton;

       public Login(WebDriver driver) {
             this.driver = driver;
       }

       public Homepage login(String username, String password) {
             userField.sendKeys(username);
             passwordField.sendKeys(password);
             loginButton.click();
             return new Homepage(driver);
       }

       public void logout() {
             logoutButton.click();
       }

}

