package app.netlify.bugbank.steps;

import app.netlify.bugbank.data.DataObjectUser;
import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.supports.RecorderSet;
import app.netlify.bugbank.utils.JsExecutor;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.Validation;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class CreateAccountStep {
    private final WebDriver driver;
    private final CreateAccountPageObject createAccountPageObject;
    private final Validation validation;
    private DataObjectUser data;

    public CreateAccountStep(WebDriver _driver) {
        driver = _driver;
        createAccountPageObject = new CreateAccountPageObject(_driver);
        validation = new Validation(_driver);
    }

    public void dataFirstUser() throws IOException {
        data = new DataObjectUser("1_user");
        register("1_user", data.getEmail(), data.getName(), data.getPassword());
    }

    public void dataSecondUser() throws IOException {
        data = new DataObjectUser("2_user");
        register("2_user", data.getEmail(), data.getName(), data.getPassword());
    }

    private void register(String dataUser, String email, String name, String password) throws IOException {
        Report.log(Status.INFO,"Fazer cadastrar novo um usuario.");
        createAccountPageObject.registerButton().click();
        fillInTheFields(email, name, password);
        JsExecutor.click(driver, createAccountPageObject.balanceAccountButton());
        createAccountPageObject.registerAccountButton().click();
        storingBankAccount(dataUser);
        successfullyRegistered();
    }

    private void fillInTheFields(String email, String name, String password) {
        Report.log(Status.INFO,"Preencha os campos");
        createAccountPageObject.registerEmailTextField().sendKeys(email);
        createAccountPageObject.nameUserTextField().sendKeys(name);
        createAccountPageObject.registerPasswordTextField().sendKeys(password);
        createAccountPageObject.confirmationPasswordTextField().sendKeys(password);
    }

    private void storingBankAccount(String dataUser) throws IOException {
        RecorderSet.ignoreTheLetters(createAccountPageObject.createdSuccessfullyModalLabel(), dataUser, "accountNumber", "digit");
    }

    private void successfullyRegistered() {
        validation.createAccountSuccess();
    }
}