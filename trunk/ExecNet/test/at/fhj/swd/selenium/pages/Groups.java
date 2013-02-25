package at.fhj.swd.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import at.fhj.swd.selenium.AbstractPage;

public class Groups extends AbstractPage {

    public Groups(WebDriver driver) {
        super(driver);
    }

    public void insertCommunity(String name, String description) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[3]/form/table/tbody/tr/td[2]/input")).sendKeys(name);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[3]/form/table/tbody/tr[2]/td[2]/input")).sendKeys(description);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[3]/form/input[2]")).click();
    }

    public void joinCommunity() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[2]/form/table/tbody/tr/td[6]/a")).click();
    }

    public void leaveCommunity() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[2]/form/table/tbody/tr/td[7]/a")).click();
    }

    public void deleteCommunity() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[2]/form/table/tbody/tr/td[5]/a")).click();
    }

    public String getPageHeadline() {
        WebElement element = driver.findElement(By.xpath("/html/body/div/table[3]/tbody/tr/td[2]/center/span"));
        return element.getText();
    }

}
