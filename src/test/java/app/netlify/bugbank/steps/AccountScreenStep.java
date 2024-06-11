package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.ValidationStep;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

public class AccountScreenStep {
    private final WebDriver driver;
    private final AccountScreenPageObject accountScreenPageObject;
    private final ValidationStep validation;

    public AccountScreenStep(WebDriver _driver) {
        driver = _driver;
        accountScreenPageObject = new AccountScreenPageObject(_driver);
        validation = new ValidationStep(_driver);
    }

    public AccountScreenStep userAccountFirst(String emailFirst, String passwordUserFirst) {
        login(emailFirst, passwordUserFirst);
        return this;
    }

    private AccountScreenStep login(String emailFirst, String passwordUserFirst) {
        Report.log(Status.INFO, "Acessar a conta primeiro do usuario");
        accountScreenPageObject.emailTextField().sendKeys(emailFirst);
        accountScreenPageObject.passwordTextField().sendKeys(passwordUserFirst);
        accountScreenPageObject.accessAccountButton().click();
        validation.firstUserAccountPage();
        accountScreenPageObject.exitAccountButton().click();
        return this;
    }
}