package app.netlify.bugbank.pageobjects;

import app.netlify.bugbank.utils.WaitElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateAccountPageObject {
    private final WebDriver driver;
    private final WaitElement wait;

    public CreateAccountPageObject(WebDriver _driver) {
        driver = _driver;
        wait = new WaitElement(_driver);
    }

    public WebElement registerButton() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[3]/button[2]"));
    }

    public WebElement registerEmailTextField() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/input"));
    }

    public WebElement nameUserTextField() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[3]/input"));
    }

    public WebElement registerPasswordTextField() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[4]/div/input"));
    }

    public WebElement confirmationPasswordTextField() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[5]/div/input"));
    }

    public WebElement balanceAccountButton() {
        return driver.findElement(By.cssSelector("#toggleAddBalance"));
    }

    public WebElement registerAccountButton() {
        return wait.toBeClickable(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/button"));
    }

    public WebElement successCloseButton() {
        return wait.toBeClickable(By.id("btnCloseModal"));
    }

    public WebElement accountNumberLabel() {
        return wait.visibilityOf(By.id("modalText"));
    }
}