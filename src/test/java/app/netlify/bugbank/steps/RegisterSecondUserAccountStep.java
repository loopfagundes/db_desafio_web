package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.supports.RecorderSet;
import app.netlify.bugbank.utils.Action;
import app.netlify.bugbank.utils.JsExecutor;
import app.netlify.bugbank.utils.Report;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegisterSecondUserAccountStep {
    private final WebDriver driver;
    private final CreateAccountPageObject createAccountPageObject;
    private final Action action;

    public RegisterSecondUserAccountStep(WebDriver _driver) {
        driver = _driver;
        createAccountPageObject = new CreateAccountPageObject(_driver);
        action = new Action(_driver);
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
        if (createAccountPageObject.balanceAccountButton().isDisplayed()) {
            createAccountPageObject.balanceAccountButton().click();
            Report.log(Status.PASS, "A botao recebeu um cilque.");
        } else if(!createAccountPageObject.balanceAccountButton().isSelected()) {
            createAccountPageObject.balanceAccountButton().click();
            action.actions(createAccountPageObject.balanceAccountButton());
            Report.log(Status.PASS, "Metodo de Actions: A botao recebeu um cilque");
        } else {
            JsExecutor.highlight(driver, createAccountPageObject.balanceAccountButton());
            Report.logCapture(Status.FAIL, "O botao nao recebeu um cilque");
        }
        createAccountPageObject.registerAccountButton().click();
        RecorderSet.recordNumbersSecondUser(createAccountPageObject.accountNumberLabel());
        createAccountPageObject.successCloseButton().click();
        return this;
    }
}