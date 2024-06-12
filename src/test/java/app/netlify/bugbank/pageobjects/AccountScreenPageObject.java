package app.netlify.bugbank.pageobjects;

import app.netlify.bugbank.utils.WaitElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountScreenPageObject {
    private final WebDriver driver;
    private final WaitElement wait;

    public AccountScreenPageObject(WebDriver _driver) {
        driver = _driver;
        wait = new WaitElement(_driver);
    }

    public WebElement emailTextField() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[1]/input"));
    }

    public WebElement passwordTextField() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[2]/div/input"));
    }

    public WebElement accessAccountButton() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[3]/button[1]"));
    }

    public WebElement helloUserLabel() {
        return wait.visibilityOf(By.id("textName"));
    }

    public WebElement balanceUserLabel() {
        return wait.visibilityOf(By.id("textBalance"));
    }

    public WebElement transferButton() {
        return wait.toBeClickable(By.id("btn-TRANSFERÊNCIA"));
    }

    public WebElement justNumberAccountTextField() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[3]/form/div[1]/div[1]/input"));
    }

    public WebElement accountDigitTextField() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[3]/form/div[1]/div[2]/input"));
    }

    public WebElement transferAmountTextField() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[3]/form/div[2]/input"));
    }

    public WebElement descriptionTextField() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[3]/form/div[3]/input"));
    }

    public WebElement transferNowButton() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[3]/form/button"));
    }

    public WebElement transferSuccessfullyLabel() {
        return wait.visibilityOf(By.id("modalText"));
    }

    public WebElement closeModalButton() {
        return wait.toBeClickable(By.id("btnCloseModal"));
    }

    public WebElement backPageButton() {
        return wait.toBeClickable(By.id("btnBack"));
    }

    public WebElement exitAccountButton() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[1]/div"));
    }

    public WebElement balanceStatementButton() {
        return wait.toBeClickable(By.id("btn-EXTRATO"));
    }

    public WebElement balanceAvailableLabel() {
        return wait.visibilityOf(By.id("textBalanceAvailable"));
    }

    public WebElement receiveValueLabel() {
        return wait.visibilityOf(By.id("textTransferValue"));
    }
}