package at.fhj.swd.selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import at.fhj.swd.selenium.AbstractPage;

public class Homepage extends AbstractPage {

    @FindBy(id = "j_idt148:entry")
    private WebElement entryField;
    @FindBy(id = "j_idt148:j_idt161")
    private WebElement dropdownboxCommunity;
    @FindBy(name = "j_idt148:j_idt164")
    private WebElement btnCreateActivity;
    @FindBy(id = "j_idt148:j_idt155PopupButton")
    private WebElement btnPopupDateFrom;
    @FindBy(id = "j_idt148:j_idt155DayCell17")
    private WebElement btnDateFrom;
    @FindBy(id = "j_idt148:j_idt159PopupButton")
    private WebElement btnPopupDateTo;
    @FindBy(id = "j_idt148:j_idt159DayCell19")
    private WebElement btnDateTo;

    public Homepage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    public void fillActivity(String entryfield) {
        getEntryField().sendKeys(entryfield);
        btnPopupDateFrom.click();
        btnDateFrom.click();
        btnPopupDateTo.click();
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
