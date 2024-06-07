package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.utils.Report;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

public class CreateAccountStep {
    private final CreateAccountPageObject createAccountPageObject;

    public CreateAccountStep(WebDriver driver) {
        createAccountPageObject = new CreateAccountPageObject(driver);
    }

    public CreateAccountStep registerFirstUsers(String email, String name, String password, String confirmationPassword) {
        firstUser(email, name, password, confirmationPassword);
        return this;
    }

    public CreateAccountStep registerSecondtUsers(String email, String name, String password, String confirmationPassword) {
        secondUser(email, name, password, confirmationPassword);
        return this;
    }

    private CreateAccountStep firstUser(String email, String name, String password, String confirmationPassword) {
        Report.log(Status.INFO, "Cadastrar uma conta primeiro usuario.");
        createAccountPageObject.registerButton().click();
        createAccountPageObject.registerEmailTextField().setText(email);
        createAccountPageObject.nameUserTextField().setText(name);
        createAccountPageObject.registerPasswordTextField().setText(password);
        createAccountPageObject.confirmationPassword().setText(confirmationPassword);
        createAccountPageObject.registerAccountButton().click();
        createAccountPageObject.successCloseButton().click();
        return this;
    }

    private CreateAccountStep secondUser(String email, String name, String password, String confirmationPassword) {
        Report.log(Status.INFO, "Cadastrar uma conta segundo usuario.");
        createAccountPageObject.registerButton().click();
        createAccountPageObject.registerEmailTextField().setText(email);
        createAccountPageObject.nameUserTextField().setText(name);
        createAccountPageObject.registerPasswordTextField().setText(password);
        createAccountPageObject.confirmationPassword().setText(confirmationPassword);
        createAccountPageObject.registerAccountButton().click();
        createAccountPageObject.successCloseButton().click();
        return this;
    }
}