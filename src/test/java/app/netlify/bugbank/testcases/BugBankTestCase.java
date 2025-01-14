package app.netlify.bugbank.testcases;

import app.netlify.bugbank.steps.AccountMovementStep;
import app.netlify.bugbank.steps.CreateAccountStep;
import app.netlify.bugbank.steps.TransferStep;
import app.netlify.bugbank.utils.BaseTest;
import app.netlify.bugbank.webdrivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class BugBankTestCase extends BaseTest {

    private WebDriver driver() {
        return DriverManager.getDriver();
    }

    @Test (
            description = "Criar novas contas dos usuários.",
            groups = {"web"},
            priority = 1
    )
    public void registerTest() throws Exception {
        CreateAccountStep register = new CreateAccountStep(driver());
        register.createNewUser("firstUser", "1_user_crypto", "1_user_crypto", "1_user", "1_user_crypto");
        driver().navigate().refresh();
        register.createNewUser("secondUser", "2_user_crypto", "2_user_crypto", "2_user", "2_user_crypto");
    }

    @Test(
            description = "Realizada a transferência bancária.",
            groups = {"web"},
            priority = 2
    )
    public void transferTest() throws Exception {
        TransferStep transfer = new TransferStep(driver());
        transfer.makeTransfer();
    }

    @Test (
            description = "Receber o saldo da transferência.",
            groups = {"web"},
            priority = 3
    )
    public void receiveTheBalanceTest() throws Exception {
        AccountMovementStep accountMovement = new AccountMovementStep(driver());
        accountMovement.receiveTheBalance();
    }
}