package app.netlify.bugbank.validations;

import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.supports.RecorderSet;
import app.netlify.bugbank.utils.Report;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ValidationStep {
    private final WebDriver driver;
    private final CreateAccountPageObject createAccountPageObject;
    private final AccountScreenPageObject accountScreenPageObject;

    public ValidationStep(WebDriver _driver) {
        driver = _driver;
        createAccountPageObject = new CreateAccountPageObject(_driver);
        accountScreenPageObject = new AccountScreenPageObject(_driver);
    }

    public ValidationStep createAccountSuccess () {
        if (RecorderSet.recordTest(createAccountPageObject.createdSuccessfullyModalLabel()).equals("A conta  foi criada com sucesso")) {
            Report.log(Status.PASS, "O usuario foi criada com sucesso");
        } else {
            Report.logCapture(Status.FAIL, "Nao foi criada sem sucesso.");
        }
        return this;
    }

    public ValidationStep firstUserAccountPage() {
        Assert.assertEquals(accountScreenPageObject.helloUserLabel().getText(), "Olá User_1,");
        Assert.assertEquals(accountScreenPageObject.balanceUserLabel().getText(), "Saldo em conta R$ 1.000,00");
        return this;
    }

    public ValidationStep transferCompletedSuccessfully() {
        Assert.assertEquals(accountScreenPageObject.transferSuccessfullyLabel().getText(),"Transferencia realizada com sucesso");
        return this;
    }

    public ValidationStep remainingBalance() {
        Assert.assertEquals(accountScreenPageObject.balanceUserLabel().getText(), "Saldo em conta R$ 500,00");
        return this;
    }

    public ValidationStep secondUserAccountPage() {
        Assert.assertEquals(accountScreenPageObject.helloUserLabel().getText(), "Olá User_2,");
        Assert.assertEquals(accountScreenPageObject.balanceUserLabel().getText(), "Saldo em conta R$ 1.500,00");
        return this;
    }

    public ValidationStep accountMovement() {
        Assert.assertEquals(accountScreenPageObject.balanceAvailableLabel().getText(), "R$ 1.500,00");
        Assert.assertEquals(accountScreenPageObject.receiveValueLabel().getText(), "R$ 500,00");
        return this;
    }
}