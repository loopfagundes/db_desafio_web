package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.pageobjects.TransferPageObject;
import app.netlify.bugbank.utils.ElementDataUtils;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.Validation;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

import static app.netlify.bugbank.utils.PropertiesManager.*;

public class TransferStep {
    private final AccountScreenPageObject accountScreenPageObject;
    private final TransferPageObject transferPageObject;
    private final Validation validation;

    public TransferStep(WebDriver driver) {
        accountScreenPageObject = new AccountScreenPageObject(driver);
        transferPageObject = new TransferPageObject(driver);
        validation = new Validation(driver);
    }

    public void makeTransfer() {
        processBankTransfer();
        logoutAccount();
    }

    private void processBankTransfer() {
        fillInTransferFields(loadProp());
        confirmTransferSuccess();
        validation.checkRemainingBalance();
    }

    private Properties loadProp() {
        return loadProperties("main", "dataUser", "secondUser");
    }

    private void fillInTransferFields(Properties userProperties) {
        transferPageObject.transferButton().click();
        Report.logCapture(Status.INFO, "Redirecionado para pagina de transferencia");
        transferPageObject.numberAccountTextField().sendKeys(userProperties.getProperty("account"));
        transferPageObject.digitTextField().sendKeys(userProperties.getProperty("digit"));
        ElementDataUtils.fakeValue(transferPageObject.transferAmountTextField(), "dataUser", "firstUser", "moneyTransferred");
        transferPageObject.descriptionTextField().sendKeys("balance transfer.");
        Report.logCapture(Status.INFO, "Preenchendo os campos do formulário. ");
    }

    private void confirmTransferSuccess() {
        if (transferPageObject.transferNowButton().isDisplayed()) {
            transferPageObject.transferNowButton().click();
            validation.transferCompletedSuccessfully();
            Report.logCapture(Status.PASS, "Transferência realizada com sucesso.");
        } else {
            Report.logCapture(Status.FAIL, "Erro ao realizar a transferência: botão não disponível.");
        }
        transferPageObject.closeModalButton().click();
        transferPageObject.backPageButton().click();
    }

    private void logoutAccount() {
        if (accountScreenPageObject.exitAccountButton().isDisplayed()) {
            accountScreenPageObject.exitAccountButton().click();
            Report.logCapture(Status.PASS, "Usuário saiu da conta com sucesso.");
        } else {
            Report.logCapture(Status.FAIL, "Erro ao sair da conta.");
        }
    }
}