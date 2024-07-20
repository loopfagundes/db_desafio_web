package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.security.SecureProperties;
import app.netlify.bugbank.supports.RecorderSet;
import app.netlify.bugbank.utils.JsExecutor;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.Validation;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static app.netlify.bugbank.security.DecrytData.*;
import static app.netlify.bugbank.security.SecureProperties.*;

public class CreateAccountStep {
    private final WebDriver driver;
    private final CreateAccountPageObject createAccountPageObject;
    private final Validation validation;

    public CreateAccountStep(WebDriver _driver) {
        driver = _driver;
        createAccountPageObject = new CreateAccountPageObject(_driver);
        validation = new Validation(_driver);
    }

    public void createNewUser(String user, String createName, String nameProp, String dataUser, String decrypto) throws Exception {
        SecureProperties.dataUser(user, createName, nameProp);
        register(dataUser, decryptoEmail(decrypto), decryptoName(decrypto), decryptoPassword(decrypto));
    }

    private void register(String dataUser, String email, String name, String password) throws IOException {
        Report.log(Status.INFO, "Fazer cadastrar novo um usuario.");
        createAccountPageObject.registerButton().click();
        fillInTheFields(email, name, password);
        JsExecutor.click(driver, createAccountPageObject.balanceAccountButton());
        createAccountPageObject.registerAccountButton().click();
        storingBankAccount(dataUser);
        successfullyRegistered();
    }

    private void fillInTheFields(String email, String name, String password) {
        Report.log(Status.INFO, "Preencha os campos");
        createAccountPageObject.registerEmailTextField().sendKeys(email);
        createAccountPageObject.nameUserTextField().sendKeys(name);
        createAccountPageObject.registerPasswordTextField().sendKeys(password);
        createAccountPageObject.confirmationPasswordTextField().sendKeys(password);
    }

    private void storingBankAccount(String dataUser) throws IOException {
        RecorderSet.ignoreTheLetters(createAccountPageObject.createdSuccessfullyModalLabel(),
                "dataUser", dataUser, "accountNumber", "digit");
    }

    private void successfullyRegistered() {
        validation.createAccountSuccess();
    }
}