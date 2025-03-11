package app.netlify.bugbank.pageobjects;

import app.netlify.bugbank.utils.WaitElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TransferPageObject {
    private final WaitElement wait;

    public TransferPageObject(WebDriver driver) {
        wait = new WaitElement(driver);
    }

    public WebElement transferButton() {
        return wait.toBeClickable(By.id("btn-TRANSFERÊNCIA"));
    }

    public WebElement numberAccountTextField() {
        return wait.toBeClickable(By.xpath("//*[@id='__next']/div/div[3]/form/div[1]/div[1]/input"));
    }

    public WebElement digitTextField() {
        return wait.toBeClickable(By.xpath("//*[@id='__next']/div/div[3]/form/div[1]/div[2]/input"));
    }

    public WebElement transferAmountTextField() {
        return wait.toBeClickable(By.xpath("//*[@id='__next']/div/div[3]/form/div[2]/input"));
    }

    public WebElement descriptionTextField() {
        return wait.toBeClickable(By.xpath("//*[@id='__next']/div/div[3]/form/div[3]/input"));
    }

    public WebElement transferNowButton() {
        return wait.toBeClickable(By.xpath("//*[@id='__next']/div/div[3]/form/button"));
    }

    public WebElement transferSuccessfullyLabel() {
        return wait.visibilityOf(By.id("modalText"));
    }

    public WebElement backPageButton() {
        return wait.toBeClickable(By.id("btnBack"));
    }

    public WebElement closeModalButton() {
        return wait.toBeClickable(By.id("btnCloseModal"));
    }
}