package at.fhj.swd.selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import at.fhj.swd.selenium.AbstractPage;

public class Homepage extends AbstractPage {

    @FindBy(xpath = "html/body/div[1]/table[3]/tbody/tr/td[2]/form/table/tbody/tr[2]/td[2]/textarea")
    private WebElement entryField;
    @FindBy(xpath = "html/body/div/table[3]/tbody/tr/td[2]/form/table/tbody/tr[5]/td[1]/select")
    private WebElement dropdownboxCommunity;
    @FindBy(xpath = "html/body/div/table[3]/tbody/tr/td[2]/form/table/tbody/tr[5]/td[2]/input")
    private WebElement btnCreateActivity;
    @FindBy(xpath = "html/body/div[1]/table[3]/tbody/tr/td[2]/form/table/tbody/tr[3]/td[2]/div/span/span[1]/img")
    private WebElement btnPopupDateFrom;
    @FindBy(
            xpath = "html/body/div[1]/table[3]/tbody/tr/td[2]/form/table/tbody/tr[3]/td[2]/div/span/table/tbody/tr[4]/td[3]")
    private WebElement btnDateFrom;
    @FindBy(xpath = "html/body/div[1]/table[3]/tbody/tr/td[2]/form/table/tbody/tr[4]/td[2]/div/span/span[1]/img")
    private WebElement btnPopupDateTo;
    @FindBy(
            xpath = "html/body/div[1]/table[3]/tbody/tr/td[2]/form/table/tbody/tr[4]/td[2]/div/span/table/tbody/tr[7]/td[7]")
    private WebElement btnDateTo;
    @FindBy(
            xpath = "html/body/div[1]/table[3]/tbody/tr/td[2]/form/table/tbody/tr[4]/td[2]/div/span/table/tbody/tr[1]/td/table/tbody/tr/td[4]/div")
    private WebElement btnNextMonth;

    public Homepage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    public void fillActivity(String entryfield) {
        getEntryField().sendKeys(entryfield);
        btnPopupDateFrom.click();
        btnDateFrom.click();
        btnPopupDateTo.click();
        btnNextMonth.click();
        btnDateTo.click();
        sendActivity();
    }

    public void sendActivity() {
        btnCreateActivity.click();
    }

    public WebElement getEntryField() {
        return entryField;
    }

    public void setEntryField(WebElement entryField) {
        this.entryField = entryField;
    }
}
