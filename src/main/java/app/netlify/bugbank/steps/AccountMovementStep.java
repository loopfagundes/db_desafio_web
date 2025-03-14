package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.utils.ElementDataUtils;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.Validation;
import app.netlify.bugbank.widgets.Element;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

public class AccountMovementStep {
    private final AccountScreenPageObject accountScreenPageObject;
    private final Validation validation;

    public AccountMovementStep(WebDriver driver) {
        accountScreenPageObject = new AccountScreenPageObject(driver);
        validation = new Validation(driver);
    }

    public void processAccountBalance() {
        viewBankStatement();
        storeAvailableBalance();
        storeIncomingTransferAmount();
        validation.checkUpdateAccountBalance();
        validation.incomingTransfer();
        logoutUser();
    }

    private void viewBankStatement() {
        Report.log(Status.INFO, "Movimento bancária do usuario");
        accountScreenPageObject.balanceStatementButton().click();
    }

    private void storeAvailableBalance() {
        ElementDataUtils.extractAndStore(accountScreenPageObject.balanceAvailableLabel(), "dataUser", "secondUser",
                "updateAccountBalance");
    }

    private void storeIncomingTransferAmount() {
        ElementDataUtils.extractAndStore(accountScreenPageObject.pendingAmountLabel(), "dataUser", "secondUser",
                "incomingTransfer");
    }

    private void logoutUser() {
        Element.click(accountScreenPageObject.logoutButton());
    }
}