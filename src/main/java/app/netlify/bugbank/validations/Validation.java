package app.netlify.bugbank.validations;

import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.pageobjects.TransferPageObject;
import app.netlify.bugbank.supports.RecorderSet;
import app.netlify.bugbank.utils.Report;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class Validation {
    private final CreateAccountPageObject createAccountPageObject;
    private final AccountScreenPageObject accountScreenPageObject;
    private final TransferPageObject transferPageObject;

    public Validation(WebDriver driver) {
        createAccountPageObject = new CreateAccountPageObject(driver);
        accountScreenPageObject = new AccountScreenPageObject(driver);
        transferPageObject = new TransferPageObject(driver);
    }

    public void createAccountSuccess() {
        if (RecorderSet.toReplaceAll(createAccountPageObject.createdSuccessfullyModalLabel()).equals("A conta  foi criada com sucesso")) {
            Report.logCapture(Status.PASS, "O usuario foi criada com sucesso");
            createAccountPageObject.successCloseModalButton().click();
        } else {
            Report.logCapture(Status.FAIL, "Ocorreu um erro no registro.");
        }
    }

    public void firstUserAccountPage() {
        Assert.assertEquals(accountScreenPageObject.helloUserLabel().getText(), "Olá User_1,");
        Assert.assertEquals(accountScreenPageObject.balanceUserLabel().getText(), "Saldo em conta R$ 1.000,00");
    }

    public void transferCompletedSuccessfully() {
        Assert.assertEquals(transferPageObject.transferSuccessfullyLabel().getText(), "Transferencia realizada com sucesso");
    }

    public void remainingBalance() throws IOException {
        Assert.assertEquals(accountScreenPageObject.balanceUserLabel().getText(),
                RecorderSet.cash(accountScreenPageObject.balanceUserLabel(), "1_user", "cashBalance"));
    }

    public void secondUserAccountPage() throws IOException {
        Assert.assertEquals(accountScreenPageObject.helloUserLabel().getText(), "Olá User_2,");
        Assert.assertEquals(accountScreenPageObject.balanceUserLabel().getText(),
                RecorderSet.cash(accountScreenPageObject.balanceUserLabel(), "2_user", "cashBalance"));
    }

    public void accountMovement() throws IOException {
        Assert.assertEquals(accountScreenPageObject.balanceAvailableLabel().getText(),
                RecorderSet.cash(accountScreenPageObject.balanceAvailableLabel(), "2_user", "balanceAvailable"));
        Assert.assertEquals(accountScreenPageObject.receiveValueLabel().getText(),
                RecorderSet.cash(accountScreenPageObject.receiveValueLabel(), "2_user", "receiveCash"));
    }
}