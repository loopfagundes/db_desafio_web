package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.utils.JsExecutor;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.Validation;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

public class CreateAccountStep {
    private final WebDriver driver;
    private final CreateAccountPageObject createAccountPageObject;
    private final Validation validation;

    public CreateAccountStep(WebDriver _driver) {
        driver = _driver;
        createAccountPageObject = new CreateAccountPageObject(_driver);
        validation = new Validation(_driver);
    }

    public void createNewUser(String email, String name, String password) {
        register(email, name, password);
    }

    private void register(String email, String name, String password) {
        Report.log(Status.INFO, "Fazer cadastrar novo um usuario.");
        createAccountPageObject.registerButton().click();
        fillInTheFields(email, name, password);
        JsExecutor.click(driver, createAccountPageObject.balanceAccountButton());
        createAccountPageObject.registerAccountButton().click();
        validation.createAccountSuccess();
    }

    private void fillInTheFields(String email, String name, String password) {
        Report.log(Status.INFO, "Preencha os campos");
        createAccountPageObject.registerEmailTextField().sendKeys(email);
        createAccountPageObject.nameUserTextField().sendKeys(name);
        createAccountPageObject.registerPasswordTextField().sendKeys(password);
        createAccountPageObject.confirmationPasswordTextField().sendKeys(password);
    }
}