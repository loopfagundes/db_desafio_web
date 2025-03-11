package app.netlify.bugbank.pageobjects;

import app.netlify.bugbank.utils.WaitElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountScreenPageObject {
    private final WaitElement wait;

    public AccountScreenPageObject(WebDriver driver) {
        wait = new WaitElement(driver);
    }

    public WebElement emailTextField() {
        return wait.toBeClickable(By.xpath("//*[@id='__next']/div/div[2]/div/div[1]/form/div[1]/input"));
    }

    public WebElement passwordTextField() {
        return wait.toBeClickable(By.xpath("//*[@id='__next']/div/div[2]/div/div[1]/form/div[2]/div/input"));
    }

    public WebElement accessAccountButton() {
        return wait.toBeClickable(By.xpath("//*[@id='__next']/div/div[2]/div/div[1]/form/div[3]/button[1]"));
    }

    public WebElement helloUserLabel() {
        return wait.visibilityOf(By.id("textName"));
    }

    public WebElement balanceUserLabel() {
        return wait.visibilityOf(By.id("textBalance"));
    }

    public WebElement exitAccountButton() {
        return wait.toBeClickable(By.xpath("//*[@id='__next']/div/div[1]/div"));
    }

    public WebElement balanceStatementButton() {
        return wait.toBeClickable(By.id("btn-EXTRATO"));
    }

    public WebElement balanceAvailableLabel() {
        return wait.visibilityOf(By.id("textBalanceAvailable"));
    }

    public WebElement amountToReceiveLabel() {
        return wait.visibilityOf(By.xpath("//*[@type='input'][@id='textTransferValue']"));
    }
}