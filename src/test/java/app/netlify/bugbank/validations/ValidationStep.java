package app.netlify.bugbank.validations;

import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ValidationStep {
    private final WebDriver driver;
    private final AccountScreenPageObject accountScreenPageObject;

    public ValidationStep(WebDriver _driver) {
        driver = _driver;
        accountScreenPageObject = new AccountScreenPageObject(_driver);
    }

    public ValidationStep helloUserValidation() {
        Assert.assertEquals(accountScreenPageObject.helloUserLabel().getText(), "Olá Paulo,");
        return this;
    }

    public ValidationStep balanceUser() {
        System.out.println(accountScreenPageObject.balanceUserLabel().getText());
        Assert.assertEquals(accountScreenPageObject.balanceUserLabel().getText(), "Saldo em conta R$ 1.000,00");
        return this;
    }
}