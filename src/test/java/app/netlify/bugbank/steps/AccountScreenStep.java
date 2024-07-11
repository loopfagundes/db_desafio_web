package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.supports.RecorderSet;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.ValidationStep;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static app.netlify.bugbank.supports.RecorderGet.*;

public class AccountScreenStep {
    private final WebDriver driver;
    private final AccountScreenPageObject accountScreenPageObject;
    private final ValidationStep validation;

    public AccountScreenStep(WebDriver _driver) {
        driver = _driver;
        accountScreenPageObject = new AccountScreenPageObject(_driver);
        validation = new ValidationStep(_driver);
    }

    public AccountScreenStep userAccountFirst(String emailFirst, String passwordUserFirst, String description) throws IOException {
        login(emailFirst, passwordUserFirst);
        makeTransfer(description);
        return this;
    }

    public AccountScreenStep userAccountSecond(String emailSecond, String passwordUserSecond) throws IOException {
        loginSecondUser(emailSecond, passwordUserSecond);
        accountMovementPage();
        return this;
    }

    private AccountScreenStep login(String emailFirst, String passwordUserFirst) {
        Report.log(Status.INFO, "Acessar a conta primeiro do usuario");
        accountScreenPageObject.emailTextField().sendKeys(emailFirst);
        accountScreenPageObject.passwordTextField().sendKeys(passwordUserFirst);
        accountScreenPageObject.accessAccountButton().click();
        validation.firstUserAccountPage();
        return this;
    }

    private AccountScreenStep makeTransfer(String description) throws IOException {
        Report.log(Status.INFO, "Pagina da minha conta e realizar a transferência");
        accountScreenPageObject.transferButton().click();
        accountScreenPageObject.justNumberAccountTextField().sendKeys(getAccountNumber("secondUser", "justNumber"));
        accountScreenPageObject.accountDigitTextField().sendKeys(getAccountNumber("secondUser", "accountDigit"));
        RecorderSet.fakeValue(accountScreenPageObject.transferAmountTextField(), "firstUser", "value");
        accountScreenPageObject.descriptionTextField().sendKeys(description);
        accountScreenPageObject.transferNowButton().click();
        validation.transferCompletedSuccessfully();
        accountScreenPageObject.closeModalButton().click();
        accountScreenPageObject.backPageButton().click();
        validation.remainingBalance();
        if (!accountScreenPageObject.exitAccountButton().isSelected()) {
            accountScreenPageObject.exitAccountButton().click();
            Report.log(Status.PASS, "O primeiro usuario saiu da conta com sucesso.");
        } else {
            Report.logCapture(Status.FAIL, "Não saiu da conta.");
        }
        return this;
    }

    private AccountScreenStep loginSecondUser(String emailSecond, String passwordUserSecond) throws IOException {
        Report.log(Status.INFO, "O segundo do usuario acessar na conta.");
        accountScreenPageObject.emailTextField().sendKeys(emailSecond);
        accountScreenPageObject.passwordTextField().sendKeys(passwordUserSecond);
        accountScreenPageObject.accessAccountButton().click();
        validation.secondUserAccountPage();
        return this;
    }

    private AccountScreenStep accountMovementPage() throws IOException {
        Report.log(Status.INFO, "Movimento do bancario do usuario");
        accountScreenPageObject.balanceStatementButton().click();
        validation.accountMovement();
        if (!accountScreenPageObject.exitAccountButton().isSelected()) {
            accountScreenPageObject.exitAccountButton().click();
            Report.log(Status.PASS, "O segundo usuario saiu da conta com sucesso.");
        } else {
            Report.logCapture(Status.FAIL, "Não saiu da conta.");
        }
        return this;
    }
}