package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import org.openqa.selenium.WebDriver;

public class CreateAccountStep {
    private final CreateAccountPageObject createAccountPageObject;

    public CreateAccountStep(WebDriver driver) {
        createAccountPageObject = new CreateAccountPageObject(driver);
    }

    public CreateAccountStep indexPage(String email, String name, String password, String confirmationPassword) {
        createAUserAccount(email, name, password, confirmationPassword);
        return this;
    }

    private CreateAccountStep createAUserAccount(String email, String name, String password, String confirmationPassword) {
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