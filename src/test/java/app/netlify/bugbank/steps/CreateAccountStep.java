package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import org.openqa.selenium.WebDriver;

public class CreateAccountStep {
    private final CreateAccountPageObject createAccountPageObject;

    public CreateAccountStep(WebDriver driver) {
        createAccountPageObject = new CreateAccountPageObject(driver);
    }

    public CreateAccountStep indexPage(String email, String name, String password) {
        createAUserAccount(email, name, password);
        return this;
    }

    private CreateAccountStep createAUserAccount(String email, String name, String password) {
        createAccountPageObject.registerButton().click();
        createAccountPageObject.emailTextField().setText(email);
        createAccountPageObject.nameUserTextField().setText(name);
        createAccountPageObject.passwordTextField().setText(password);
        return this;
    }
}