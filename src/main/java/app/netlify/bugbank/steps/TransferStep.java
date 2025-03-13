package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.pageobjects.TransferPageObject;
import app.netlify.bugbank.utils.ElementDataUtils;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.Validation;
import app.netlify.bugbank.widgets.Element;

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
        logoutUser();
    }

    private void processBankTransfer() {
        accessTransfer();
        fillInTransferFields(loadProp());
        submitTransfer();
        closeModal();
        returnToPreviousPage();
        validation.checkRemainingBalance();
    }

    private Properties loadProp() {
        return loadProperties("main", "dataUser", "secondUser");
    }

    private void accessTransfer() {
        Element.click(transferPageObject.transferButton());
    }

    private void fillInTransferFields(Properties userProperties) {
        Report.logCapture(Status.INFO, "Redirecionado para pagina de transferencia");
        transferPageObject.numberAccountTextField().sendKeys(userProperties.getProperty("account"));
        transferPageObject.digitTextField().sendKeys(userProperties.getProperty("digit"));
        ElementDataUtils.fakeValue(transferPageObject.transferAmountTextField(), "dataUser", "firstUser", "moneyTransferred");
        transferPageObject.descriptionTextField().sendKeys("balance transfer.");
        Report.logCapture(Status.INFO, "Preenchendo os campos do formulário. ");
    }

    private void submitTransfer() {
        Element.click(transferPageObject.transferNowButton());
        validation.transferCompletedSuccessfully();
    }

    private void closeModal() {
        Element.click(transferPageObject.closeModalButton());
    }

    private void returnToPreviousPage() {
        Element.click(transferPageObject.returnButton());
    }

    private void logoutUser() {
        Element.click(accountScreenPageObject.logoutButton());
    }
}