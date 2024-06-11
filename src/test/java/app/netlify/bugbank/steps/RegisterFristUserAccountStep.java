package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.supports.RecorderSet;
import app.netlify.bugbank.utils.Action;
import app.netlify.bugbank.utils.JsExecutor;
import app.netlify.bugbank.utils.Report;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static app.netlify.bugbank.utils.JsExecutor.*;

public class RegisterFristUserAccountStep {
    private final WebDriver driver;
    private final CreateAccountPageObject createAccountPageObject;
    private final Action action;



    public RegisterFristUserAccountStep(WebDriver _driver) {
        driver = _driver;
        createAccountPageObject = new CreateAccountPageObject(_driver);
        action = new Action(_driver);
    }

    public RegisterFristUserAccountStep indexPage(String emailFirst, String userFirst, String passwordUserFirst) throws IOException {
        firstUser(emailFirst, userFirst, passwordUserFirst);
        return this;
    }

    private RegisterFristUserAccountStep firstUser(String emailFirst, String userFirst, String passwordUserFirst) throws IOException {
        Report.log(Status.INFO, "Cadastrar uma conta primeiro usuario.");
        createAccountPageObject.registerButton().click();
        createAccountPageObject.registerEmailTextField().sendKeys(emailFirst);
        createAccountPageObject.nameUserTextField().sendKeys(userFirst);
        createAccountPageObject.registerPasswordTextField().sendKeys(passwordUserFirst);
        createAccountPageObject.confirmationPasswordTextField().sendKeys(passwordUserFirst);
        JsExecutor.click(driver, createAccountPageObject.balanceAccountButton());
        createAccountPageObject.registerAccountButton().click();
        RecorderSet.recordNumbersFirstUser(createAccountPageObject.accountNumberLabel());
        System.out.println("PRIMEIRO USUARIO: " + createAccountPageObject.accountNumberLabel().getText());
        createAccountPageObject.successCloseButton().click();
        return this;
    }
}