package app.netlify.bugbank.testcases;

//import app.netlify.bugbank.steps.AccountMovementStep;
import app.netlify.bugbank.dto.UserDataDTO;
import app.netlify.bugbank.steps.CreateAccountStep;
//import app.netlify.bugbank.steps.TransferStep;
import app.netlify.bugbank.utils.BaseTest;
import app.netlify.bugbank.webdrivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

import static app.netlify.bugbank.dto.UserDataDTO.*;

public class BugBankTestCase extends BaseTest {

    private WebDriver driver() {
        return DriverManager.getDriver();
    }

    @Test (
            description = "Acessar na tela de login e criar novas contas dos usuários.",
            groups = {"web"},
            priority = 1
    )
    public void registerTest() throws IOException {
        CreateAccountStep createAccountStep = new CreateAccountStep(driver());
        createAccountStep.createNewUser(firstUserData().getEmail(), firstUserData().getName(), firstUserData().getPassword(), "firstUser");
        driver().navigate().refresh();
        createAccountStep.createNewUser(secondUserData().getEmail(), secondUserData().getName(), secondUserData().getPassword(), "secondUser");
    }

    // @Test(
    //         description = "Realizada a transferência bancária.",
    //         groups = {"web"},
    //         priority = 2
    // )
    // public void transferTest() throws Exception {
    //     TransferStep transfer = new TransferStep(driver());
    //     transfer.makeTransfer();
    // }

    // @Test (
    //         description = "Receber o saldo da transferência.",
    //         groups = {"web"},
    //         priority = 3
    // )
    // public void receiveTheBalanceTest() throws Exception {
    //     AccountMovementStep accountMovement = new AccountMovementStep(driver());
    //     accountMovement.receiveTheBalance();
    // }
}