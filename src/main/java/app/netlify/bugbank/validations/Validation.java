package app.netlify.bugbank.validations;

import app.netlify.bugbank.dto.UserModelDTO;
import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.pageobjects.TransferPageObject;
import app.netlify.bugbank.utils.PropertiesManager;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.utils.ElementDataUtils;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static app.netlify.bugbank.utils.PropertiesManager.*;

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
        String modalText = ElementDataUtils.toReplaceAll(createAccountPageObject.successModalLabel()).trim();
        String expectedMessage = "A conta  foi criada com sucesso";
        if (modalText.equals(expectedMessage)) {
            Report.logCapture(Status.PASS, "O usuário foi criado com sucesso.");
            createAccountPageObject.closeSuccessModalButton().click();
        } else {
            Report.log(Status.FAIL, "Erro ao criar conta. Mensagem exibida: " + modalText);
            Assert.fail("Falha ao criar a conta. Mensagem inesperada: " + modalText);
        }
    }

    public void firstUserAccount(UserModelDTO user) {
        String expectedGreeting = "Olá " + user.getName() + ",";
        String actualGreeting = accountScreenPageObject.userGreetingLabel().getText();
        Assert.assertEquals(actualGreeting, expectedGreeting, "A saudação do usuário não corresponde ao esperado.");
    }

    public void checkStoredBalanceForFirstUser() {
        String expectedBalance = "Saldo em conta R$ 1.000,00";
        String actualBalance = accountScreenPageObject.userBalanceLabel().getText();
        Assert.assertEquals(actualBalance, expectedBalance, "O saldo do primeiro usuario não corresponde ao esperado.");
    }

    public void transferCompletedSuccessfully() {
        Assert.assertEquals(transferPageObject.transferSuccessLabel().getText(),
                "Transferencia realizada com sucesso",
                "Mensagem de sucesso da transferência não corresponde ao esperado.");
    }

    public void checkRemainingBalance() {
        String remainingBalance = getRemainingBalance();
        String storedBalance = getStoredBalance();
        Assert.assertEquals(remainingBalance, storedBalance, "Os valores do saldo restante não correspondem!");
        Report.logCapture(Status.PASS, "Os valores do saldo restante estão corretos!");
    }

    private String getRemainingBalance() {
        String remainingBalance = accountScreenPageObject.userBalanceLabel().getText().trim();
        if (remainingBalance.isEmpty()) {
            Report.logCapture(Status.FAIL, "Falha ao capturar o saldo restante: valor vazio ou null.");
            Assert.fail("Saldo restante não pode ser null ou vazio.");
        }
        setProperty("main", "dataUser", "firstUser", "remainingBalance", remainingBalance);
        return remainingBalance;
    }

    private static String getStoredBalance() {
        String loadStoredBalance = loadProperties("main", "dataUser", "firstUser").getProperty("remainingBalance");
        if (loadStoredBalance == null || loadStoredBalance.trim().isEmpty()) {
            Report.logCapture(Status.FAIL, "Falha ao carregar saldo armazenado: valor null ou vazio.");
            Assert.fail("Erro ao carregar saldo armazenado.");
        }
        return loadStoredBalance;
    }

    public void validateSecondUserAccount(UserModelDTO user) {
        String actualGreeting = accountScreenPageObject.userGreetingLabel().getText();
        String expectedGreeting = "Olá " + user.getName() + ",";
        Assert.assertEquals(actualGreeting, expectedGreeting, "A saudação do segundo usuário não está correta.");
    }

    public void checkStoredBalanceForSecondUser() {
        String fetchStoredBalance = PropertiesManager.loadProperties("main", "dataUser", "secondUser").getProperty("accountBalance");
        String actualBalance = accountScreenPageObject.userBalanceLabel().getText();
        Assert.assertEquals(actualBalance, fetchStoredBalance, "O saldo do segundo usuario não corresponde ao esperado.");
    }

    public void accountMovement() {
        String actualBalanceAvailable = accountScreenPageObject.balanceAvailableLabel().getText();
        String expectedBalanceAvailable = PropertiesManager.loadProperties("main", "dataUser", "secondUser").getProperty("updateAccountBalance");
        Assert.assertEquals(actualBalanceAvailable, expectedBalanceAvailable, "O saldo disponivel do segundo usuario não corresponde ao esperado.");

//        Assert.assertEquals(accountScreenPageObject.receiveValueLabel().getText(),
//                RecorderSet.cash(accountScreenPageObject.receiveValueLabel(), "dataUser", "2_user", "receiveCash"));

        Report.logCapture(Status.INFO, "Observer o extrato do Usuario 2.");
    }
}