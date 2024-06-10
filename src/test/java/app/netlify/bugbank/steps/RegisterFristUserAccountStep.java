package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.utils.Action;
import app.netlify.bugbank.utils.FilesOperation;
import app.netlify.bugbank.utils.Report;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

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
        if (createAccountPageObject.balanceAccountButton().isDisplayed()) {
            createAccountPageObject.balanceAccountButton().click();
            Report.log(Status.PASS, "A botao recebeu um cilque.");
        } else {
            action.actions(createAccountPageObject.balanceAccountButton());
            Report.log(Status.PASS, "Metodo de Actions: A botao recebeu um cilque");
        }
        createAccountPageObject.registerAccountButton().click();

        String accountNumber = createAccountPageObject.accountNumberLabel().getText();
        String[] numberSeparator = accountNumber.split("-");
        String justNumber = numberSeparator[0].replaceAll("[^0-9]", "");
        String accountDigit = numberSeparator[1].replaceAll("[^0-9]", "");

        FilesOperation.setProperty("firstUser", "justNumber", justNumber);
        FilesOperation.setProperty("firstUser", "accountDigit", accountDigit);

        createAccountPageObject.successCloseButton().click();
        return this;
    }
}