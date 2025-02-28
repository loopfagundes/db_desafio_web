package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.utils.ElementTextParser;
import app.netlify.bugbank.utils.JsExecutor;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.Validation;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class CreateAccountStep {
    private final WebDriver driver;
    private final CreateAccountPageObject createAccountPage;
    private final Validation validation;

    public CreateAccountStep(WebDriver _driver) {
        driver = _driver;
        createAccountPage = new CreateAccountPageObject(_driver);
        validation = new Validation(_driver);
    }

    public void createNewUser(String email, String name, String password, String userProp) throws IOException {
        Report.log(Status.INFO, "Iniciando cadastro de novo usuário.");
        registerUser(email, name, password, userProp);
    }

    private void registerUser(String email, String name, String password, String userProp) throws IOException {
        createAccountPage.registerButton().click();
        fillRegistrationFields(email, name, password);
        submitRegistration();
        parseAccountData(userProp);
        validation.createAccountSuccess();
    }

    private void fillRegistrationFields(String email, String name, String password) {
        Report.log(Status.INFO, "Preenchendo os campos do formulário.");
        createAccountPage.registerEmailTextField().sendKeys(email);
        createAccountPage.nameUserTextField().sendKeys(name);
        createAccountPage.registerPasswordTextField().sendKeys(password);
        createAccountPage.confirmationPasswordTextField().sendKeys(password);
    }

    private void submitRegistration() {
        JsExecutor.click(driver, createAccountPage.balanceAccountButton());
        createAccountPage.registerAccountButton().click();
    }

    private void parseAccountData(String userProp) throws IOException {
        Report.log(Status.INFO, "Extraindo dados da conta criada.");
        ElementTextParser.ignoreTheLetters(
                createAccountPage.createdSuccessfullyModalLabel(),
                userProp,
                "account",
                "digit"
        );
    }
}