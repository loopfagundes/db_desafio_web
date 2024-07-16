package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.pageobjects.TransferPageObject;
import app.netlify.bugbank.supports.RecorderSet;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.Validation;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static app.netlify.bugbank.supports.RecorderGet.getDataUser;
import static app.netlify.bugbank.utils.security.DecrytData.*;

public class TransferStep {
    private final AccountScreenPageObject accountScreenPageObject;
    private final TransferPageObject transferPageObject;
    private final Validation validation;

    public TransferStep(WebDriver driver) {
        accountScreenPageObject = new AccountScreenPageObject(driver);
        transferPageObject = new TransferPageObject(driver);
        validation = new Validation(driver);
    }

    public void makeTransfer() throws Exception {
        login(decryptoEmail("1_user_crypto"), decryptoPassword("1_user_crypto"));
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
        transferPageObject.transferButton().click();
        fillInTransferFields();
        transferCompletedSuccessfully();
        accountScreenPageObject.backPageButton().click();
        validation.remainingBalance();
        exitAccount();
    }

    private void fillInTransferFields() throws IOException {
        transferPageObject.numberAccountTextField().sendKeys(getDataUser("dataUser","2_user", "accountNumber"));
        transferPageObject.digitTextField().sendKeys(getDataUser("dataUser","2_user", "digit"));
        RecorderSet.fakeValue(transferPageObject.transferAmountTextField(), "dataUser","1_user", "value");
        transferPageObject.descriptionTextField().sendKeys("balance transfer.");
        Report.logCapture(Status.INFO, "Observer nos campos de transferencia.");
    }

    private void transferCompletedSuccessfully() {
        if(!transferPageObject.transferNowButton().isSelected()) {
            transferPageObject.transferNowButton().click();
            validation.transferCompletedSuccessfully();
            Report.logCapture(Status.PASS, "Transferencia realizada com sucesso.");
        } else {
            Report.logCapture(Status.FAIL, "Ocorreu um erro na transferencia.");
        }
        transferPageObject.closeModalButton().click();
    }

    private void exitAccount() {
        if (!accountScreenPageObject.exitAccountButton().isSelected()) {
            accountScreenPageObject.exitAccountButton().click();
            Report.logCapture(Status.PASS, "O primeiro usuario saiu da conta com sucesso.");
        } else {
            Report.logCapture(Status.FAIL, "Não saiu da conta.");
        }
    }
}
