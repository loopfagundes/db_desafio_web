package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.supports.RecorderSet;
import app.netlify.bugbank.utils.JsExecutor;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.ValidationStep;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegisterSecondUserAccountStep {
    private final WebDriver driver;
    private final CreateAccountPageObject createAccountPageObject;
    private final ValidationStep validationStep;

    public RegisterSecondUserAccountStep(WebDriver _driver) {
        driver = _driver;
        createAccountPageObject = new CreateAccountPageObject(_driver);
        validationStep = new ValidationStep(_driver);
    }

    public RegisterSecondUserAccountStep indexPage(String secondEmail, String userSecond, String passwordUserSecond) throws IOException {
        secondUser(secondEmail, userSecond, passwordUserSecond);
        return this;
    }

    private RegisterSecondUserAccountStep secondUser(String secondEmail, String userSecond, String passwordUserSecond) throws IOException {
        Report.log(Status.INFO, "Cadastrar uma conta segundo usuario.");
        createAccountPageObject.registerButton().click();
        createAccountPageObject.registerEmailTextField().sendKeys(secondEmail);
        createAccountPageObject.nameUserTextField().sendKeys(userSecond);
        createAccountPageObject.registerPasswordTextField().sendKeys(passwordUserSecond);
        createAccountPageObject.confirmationPasswordTextField().sendKeys(passwordUserSecond);
        JsExecutor.click(driver, createAccountPageObject.balanceAccountButton());
        createAccountPageObject.registerAccountButton().click();
        RecorderSet.ignoreTheLetters(createAccountPageObject.createdSuccessfullyModalLabel(), "secondUser", "justNumber", "accountDigit");
        validationStep.createAccountSuccess();
        createAccountPageObject.successCloseModalButton().click();
        return this;
    }
}