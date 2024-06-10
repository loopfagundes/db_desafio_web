package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.utils.Action;
import app.netlify.bugbank.utils.FilesOperation;
import app.netlify.bugbank.utils.Report;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegisterSecondUserAccountStep {
    private final CreateAccountPageObject createAccountPageObject;
    private final Action action;

    public RegisterSecondUserAccountStep(WebDriver _driver) {
        createAccountPageObject = new CreateAccountPageObject(_driver);
        action = new Action(_driver);
    }

    public RegisterSecondUserAccountStep indexPage(String secondEmail, String userSecond, String passwordUserSecond) throws IOException {
        secondUser(secondEmail, userSecond, passwordUserSecond);
        return this;
    }

    private RegisterSecondUserAccountStep secondUser(String secondEmail, String userSecond, String passwordUserSecond) throws IOException {
        Report.log(Status.INFO, "Cadastrar uma conta segundo usuario.");
        createAccountPageObject.registerButton().click();
        createAccountPageObject.registerEmailTextField().sendKeys(secondEmail);
        createAccountPageObject.nameUserTextField().sendKeys(userSecond);
        createAccountPageObject.registerPasswordTextField().sendKeys(passwordUserSecond);
        createAccountPageObject.confirmationPasswordTextField().sendKeys(passwordUserSecond);
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

        FilesOperation.setProperty("secondUser", "justNumber", justNumber);
        FilesOperation.setProperty("secondUser", "accountDigit", accountDigit);

        createAccountPageObject.successCloseButton().click();
        return this;
    }
}