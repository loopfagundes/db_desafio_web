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
}