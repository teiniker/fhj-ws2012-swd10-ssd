package at.fhj.swd.selenium.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Documents {

    WebDriver driver;

    public Documents(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public String uploadDocument(String document) throws InterruptedException {
        String strRet = null;

        // get document from resources
        String documentPath = this.getClass().getResource("../test/resources/" + document).toExternalForm();

        // upload document
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(documentPath);
        driver.findElement(By.xpath("//span[2]/span")).click();

        Thread.sleep(1000);

        // get last uploaded document
        strRet = driver.findElement(By.xpath("//tr[last()]/td[1]/span")).getText();


        // cleanup
        // delete last uploaded document
        // driver.findElement(By.xpath("//tr[last()]/td[5]/a/img")).click();
        return strRet;
    }

    public void selectDocumentCommunity(int pos) throws InterruptedException {

        // select
        driver.findElement(By.xpath("//td/div/table/tbody/tr[" + pos + "]")).click();
        Thread.sleep(1000);
    }

    public boolean isDocumentCommunitySelected(int pos) {
        boolean bRet = false;

        // verify selection with class attribute
        String cssClass = driver.findElement(By.xpath("//td/div/table/tbody/tr[" + pos + "]")).getAttribute("class");
        bRet = cssClass.equals("rf-edt-r-sel") || cssClass.equals("rf-edt-r-sel rf-edt-r-act");
        return bRet;
    }

    public int getDocumentsCount() {
        return driver.findElements(By.xpath("//tr/td[2]/div/form/table/tbody/tr/td[1]/span")).size();
    }

    public void deleteDocument() throws InterruptedException {
        // tr/td[2]/div/form/table/tbody/tr/td[5]/a/img
        driver.findElement(By.xpath("//tr[last()]/td[5]/a[1]")).click();
        Thread.sleep(1000);
    }
}
