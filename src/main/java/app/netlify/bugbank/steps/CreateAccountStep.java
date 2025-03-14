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

    public void registerNewUser(String email, String name, String password, String userProp) {
        performUserRegistration(email, name, password, userProp);
    }

    private void performUserRegistration(String email, String name, String password, String userProp) {
        Report.log(Status.INFO, "Iniciando cadastro de novo usuário.");
        Element.click(createAccountPage.registerButton());
        enterUserDetails(email, name, password);
        enableInitialBalance();
        confirmRegistration();
        storeAccountDetails(userProp);
        validation.accountCreatedSuccessfully();
        closeSuccessModal();
    }

    private void enterUserDetails(String email, String name, String password) {
        createAccountPage.registerEmailTextField().sendKeys(email);
        createAccountPage.userNameTextField().sendKeys(name);
        createAccountPage.registerPasswordTextField().sendKeys(password);
        createAccountPage.confirmationPasswordTextField().sendKeys(password);
    }

    private void enableInitialBalance() {
        Element.jsClick(driver, createAccountPage.balanceAccountButton());
    }

    private void confirmRegistration() {
        Element.click(createAccountPage.registerAccountButton());
    }

    private void closeSuccessModal() {
        Element.click(createAccountPage.closeSuccessModalButton());
    }

    private void storeAccountDetails(String userProp) {
        Report.log(Status.INFO, "Extraindo dados da conta criada.");
        ElementDataUtils.extractAccountDetails(
                createAccountPage.successModalLabel(),
                userProp,
                "account",
                "digit");
    }
}