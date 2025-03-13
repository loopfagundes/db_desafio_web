package app.netlify.bugbank.testcases;

import app.netlify.bugbank.dto.UserModelDTO;
import app.netlify.bugbank.steps.AccountMovementStep;
import app.netlify.bugbank.steps.CreateAccountStep;
import app.netlify.bugbank.steps.LoginStep;
import app.netlify.bugbank.steps.TransferStep;
import app.netlify.bugbank.utils.BaseTest;
import app.netlify.bugbank.webdrivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static app.netlify.bugbank.dto.UserDataDTO.*;

public class BugBankTestCase extends BaseTest {

    private WebDriver driver() {
        return DriverManager.getDriver();
    }

    @Test(
            description = "Acessa a tela de login e criar novas contas de usuários.",
            groups = {"web"},
            priority = 1
    )
    public void registerTest() {
        CreateAccountStep createAccountStep = new CreateAccountStep(driver());
        registerUser(createAccountStep, firstUserData(), "firstUser");
        driver().navigate().refresh();
        registerUser(createAccountStep, secondUserData(), "secondUser");
    }

    private void registerUser(CreateAccountStep createAccountStep, UserModelDTO userData, String userProp) {
        createAccountStep.createNewUser(userData.getEmail(), userData.getName(), userData.getPassword(), userProp);
    }

    @Test(
            description = "Efetue o login do usuário primeiro",
            groups = {"web"},
            priority = 2)
    public void loginFirstUserTest() {
        new LoginStep(driver()).loginAsFirstUser();
    }

    @Test(
            description = "Realizada a transferência bancária.",
            groups = {"web"},
            priority = 3
    )
    public void transferTest() {
        new TransferStep(driver()).makeTransfer();
    }

    @Test(
            description = "Efetue o login do usuário segundo",
            groups = {"web"},
            priority = 4)
    public void loginSecondUserTest() {
        new LoginStep(driver()).loginAsSecondUser();
    }

     @Test (
             description = "Receber o saldo da transferência.",
             groups = {"web"},
             priority = 5
     )
     public void accountMovementTest() {
         AccountMovementStep accountMovement = new AccountMovementStep(driver());
         accountMovement.processAccountBalance();
     }
}