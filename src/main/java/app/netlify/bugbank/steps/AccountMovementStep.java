package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.Validation;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static app.netlify.bugbank.security.DecrytData.*;

public class AccountMovementStep {
    private final AccountScreenPageObject accountScreenPageObject;
    private final Validation validation;

    public AccountMovementStep(WebDriver driver) {
        accountScreenPageObject = new AccountScreenPageObject(driver);
        validation = new Validation(driver);
    }

    public void receiveTheBalance() throws Exception {
        login(decryptoEmail("2_user_crypto"), decryptoPassword("2_user_crypto"));
        bankStatement();
    }

    private void login(String emailSecond, String passwordUserSecond) throws IOException {
        Report.log(Status.INFO, "O segundo do usuario acessar na conta.");
        accountScreenPageObject.emailTextField().sendKeys(emailSecond);
        accountScreenPageObject.passwordTextField().sendKeys(passwordUserSecond);
        accountScreenPageObject.accessAccountButton().click();
        validation.secondUserAccountPage();
    }

    private void bankStatement() throws IOException {
        Report.log(Status.INFO, "Movimento bancária do usuario");
        accountScreenPageObject.balanceStatementButton().click();
        validation.accountMovement();
        if (!accountScreenPageObject.exitAccountButton().isSelected()) {
            accountScreenPageObject.exitAccountButton().click();
            Report.logCapture(Status.PASS, "O segundo usuario saiu da conta com sucesso.");
        } else {
            Report.logCapture(Status.FAIL, "Não saiu da conta.");
        }
    }
}