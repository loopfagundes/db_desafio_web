package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.utils.Report;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

public class RegisterSecondUserAccountStep {
    private final CreateAccountPageObject createAccountPageObject;

    public RegisterSecondUserAccountStep(WebDriver _driver) {
        createAccountPageObject = new CreateAccountPageObject(_driver);
    }

    public RegisterSecondUserAccountStep indexPage(String secondEmail, String userSecond, String passwordUserSecond) {
        secondUser(secondEmail, userSecond, passwordUserSecond);
        return this;
    }

    private RegisterSecondUserAccountStep secondUser(String secondEmail, String userSecond, String passwordUserSecond) {
        Report.log(Status.INFO, "Cadastrar uma conta segundo usuario.");
        createAccountPageObject.registerButton().click();
        createAccountPageObject.registerEmailTextField().sendKeys(secondEmail);
        createAccountPageObject.nameUserTextField().sendKeys(userSecond);
        createAccountPageObject.registerPasswordTextField().sendKeys(passwordUserSecond);
        createAccountPageObject.confirmationPasswordTextField().sendKeys(passwordUserSecond);
        createAccountPageObject.balanceAccountButton().click();
        createAccountPageObject.registerAccountButton().click();
        createAccountPageObject.successCloseButton().click();
        return this;
    }
}