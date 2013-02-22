package at.fhj.swd.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import at.fhj.swd.selenium.AbstractPage;


public class User extends AbstractPage {

    public User(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    public String SendEmail(String to, String subject, String body) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[1]/form/table/tbody/tr[1]/td[2]/input"))
            .sendKeys(to);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[1]/form/table/tbody/tr[2]/td[2]/input"))
            .sendKeys(subject);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[1]/form/table/tbody/tr[3]/td[2]/textarea"))
            .sendKeys(body);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[1]/form/input[2]")).click();

        WebElement element = driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[2]/ul/li"));
        return element.getText();
    }

    public String SearchUser(String user) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td/form/table/tbody/tr/td[2]/input")).sendKeys(
            user);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td/form/input[2]")).click();

        WebElement element = driver.findElement(By
            .xpath("/html/body/div/table[3]/tbody/tr/td[2]/table/tbody/tr/td[2]/span"));
        return element.getText();
    }

}
