package app.netlify.bugbank.steps;

import app.netlify.bugbank.data.DataObjectUser;
import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.supports.RecorderSet;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.Validation;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static app.netlify.bugbank.supports.RecorderGet.getDataUser;

public class TransferStep {
    private final AccountScreenPageObject accountScreenPageObject;
    private final Validation validation;

    public TransferStep(WebDriver driver) {
        accountScreenPageObject = new AccountScreenPageObject(driver);
        validation = new Validation(driver);
    }

    public void makeTransfer() throws IOException {
        DataObjectUser data = new DataObjectUser("1_user");
        login(data.getEmail(), data.getPassword());
        transferBankBalance();
    }

    private void login(String email, String password) {
        Report.log(Status.INFO, "Acessar a conta primeiro do usuario");
        accountScreenPageObject.emailTextField().sendKeys(email);
        accountScreenPageObject.passwordTextField().sendKeys(password);
        accountScreenPageObject.accessAccountButton().click();
        validation.firstUserAccountPage();
    }

    private void transferBankBalance() throws IOException {
        Report.log(Status.INFO, "Pagina da minha conta e realizar a transferência");
        accountScreenPageObject.transferButton().click();
        accountScreenPageObject.numberAccountTextField().sendKeys(getDataUser("2_user", "accountNumber"));
        accountScreenPageObject.digitTextField().sendKeys(getDataUser("2_user", "digit"));
        RecorderSet.fakeValue(accountScreenPageObject.transferAmountTextField(), "1_user", "value");
        accountScreenPageObject.descriptionTextField().sendKeys(getDataUser("1_user", "description"));
        accountScreenPageObject.transferNowButton().click();
        validation.transferCompletedSuccessfully();
        accountScreenPageObject.closeModalButton().click();
        accountScreenPageObject.backPageButton().click();
        validation.remainingBalance();
        if (!accountScreenPageObject.exitAccountButton().isSelected()) {
            accountScreenPageObject.exitAccountButton().click();
            Report.logCapture(Status.PASS, "O primeiro usuario saiu da conta com sucesso.");
        } else {
            Report.logCapture(Status.FAIL, "Não saiu da conta.");
        }
    }
}
