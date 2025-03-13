package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.utils.ElementDataUtils;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.Validation;
import app.netlify.bugbank.widgets.Element;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

public class CreateAccountStep {
    private final WebDriver driver;
    private final CreateAccountPageObject createAccountPage;
    private final Validation validation;

    public CreateAccountStep(WebDriver _driver) {
        driver = _driver;
        createAccountPage = new CreateAccountPageObject(_driver);
        validation = new Validation(_driver);
    }

    public void createNewUser(String email, String name, String password, String userProp) {
        registerUser(email, name, password, userProp);
    }

    private void registerUser(String email, String name, String password, String userProp) {
        Report.log(Status.INFO, "Iniciando cadastro de novo usuário.");
        Element.click(createAccountPage.registerButton());
        fillRegistrationFields(email, name, password);
        addBalanceToAccount();
        submitRegistration();
        extractAccountDetails(userProp);
        validation.createAccountSuccess();
    }

    private void fillRegistrationFields(String email, String name, String password) {
        createAccountPage.registerEmailTextField().sendKeys(email);
        createAccountPage.userNameTextField().sendKeys(name);
        createAccountPage.registerPasswordTextField().sendKeys(password);
        createAccountPage.confirmationPasswordTextField().sendKeys(password);
    }

    private void addBalanceToAccount() {
        Element.jsClick(driver, createAccountPage.balanceAccountButton());
    }

    private void submitRegistration() {
        Element.click(createAccountPage.registerAccountButton());
    }

    private void extractAccountDetails(String userProp) {
        Report.log(Status.INFO, "Extraindo dados da conta criada.");
        ElementDataUtils.extractAccountDetails(
                createAccountPage.successModalLabel(),
                userProp,
                "account",
                "digit"
        );
    }
}