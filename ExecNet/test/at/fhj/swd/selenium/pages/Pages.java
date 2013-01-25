package at.fhj.swd.selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Pages {

       private WebDriver driver;

       public Pages(WebDriver driver) {
             this.driver = driver;
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

}
