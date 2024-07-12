package app.netlify.bugbank.testcases;

import app.netlify.bugbank.steps.AccountMovementStep;
import app.netlify.bugbank.steps.CreateAccountStep;
import app.netlify.bugbank.steps.TransferStep;
import app.netlify.bugbank.utils.BaseTest;
import app.netlify.bugbank.utils.Property;
import app.netlify.bugbank.webdrivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class BugBankTestCase extends BaseTest {

    @Test(
            description = "Criar duas contas com saldo e realizar uma transferência valor para outra.",
            groups = {"web"}
    )
    public void BugBank() throws IOException {
        WebDriver driver = DriverManager.getDriver();
        driver.get(Property.get("url"));
        CreateAccountStep register = new CreateAccountStep(driver);
        TransferStep transfer = new TransferStep(driver);
        AccountMovementStep accountMovement = new AccountMovementStep(driver);
        register.dataFirstUser();
        driver.navigate().refresh();
        register.dataSecondUser();
        transfer.makeTransfer();
        accountMovement.receiveTheBalance();
    }
}