package app.netlify.bugbank.validations;

import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.pageobjects.TransferPageObject;
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
        String modalText = ElementDataUtils.toReplaceAll(createAccountPageObject.createdSuccessfullyModalLabel()).trim();
        if (modalText.equals("A conta  foi criada com sucesso")) {
            Report.logCapture(Status.PASS, "O usuário foi criado com sucesso.");
            createAccountPageObject.successCloseModalButton().click();
        } else {
            Report.log(Status.FAIL, "Ocorreu um erro no registro. Mensagem exibida: " + modalText);
            Assert.fail("Falha ao criar a conta. Mensagem inesperada: " + modalText);
        }
    }

    public void firstUserInAccount() {
        Assert.assertEquals(accountScreenPageObject.helloUserLabel().getText(), "Olá User_1,");
        Assert.assertEquals(accountScreenPageObject.balanceUserLabel().getText(), "Saldo em conta R$ 1.000,00");
    }

    public void transferCompletedSuccessfully() {
        Assert.assertEquals(transferPageObject.transferSuccessfullyLabel().getText(), "Transferencia realizada com sucesso");
    }

    public void remainingBalance() {
        String remainingBalance = getRemainingBalance();
        String storedBalance = getStoredBalance();
        boolean isBalanceCorrect = remainingBalance.equals(storedBalance);
        Report.logCapture(isBalanceCorrect ? Status.PASS : Status.FAIL, "Os valores do saldo estão corretos!");
        Assert.assertEquals(remainingBalance, storedBalance, "Os valores do saldo não correspondem!");
    }

    private String getRemainingBalance() {
        String remainingBalance = accountScreenPageObject.balanceUserLabel().getText();
        if (remainingBalance.trim().isEmpty()) {
            Report.logCapture(Status.FAIL, "Falha ao capturar o saldo restante: valor vazio ou null.");
            Assert.fail("Saldo restante não pode ser nulo ou vazio.");
        }
        setProperty("main", "dataUser", "firstUser", "remainingBalance", remainingBalance);
        return remainingBalance;
    }

    private static String getStoredBalance() {
        String loadStoredBalance = loadProperties("main", "dataUser", "firstUser").getProperty("remainingBalance");
        if (loadStoredBalance == null || loadStoredBalance.trim().isEmpty()) {
            Report.logCapture(Status.FAIL, "Falha o saldo armazenado: valor nulo ou vazio.");
            Assert.fail("Erro ao carregar saldo armazenado.");
        }
        return loadStoredBalance;
    }

//
//    public void secondUserAccountPage() throws IOException {
//        Assert.assertEquals(accountScreenPageObject.helloUserLabel().getText(), "Olá User_2,");
//        Assert.assertEquals(accountScreenPageObject.balanceUserLabel().getText(),
//                RecorderSet.cash(accountScreenPageObject.balanceUserLabel(), "dataUser", "2_user", "cashBalance"));
//        Report.logCapture(Status.INFO, "Observer o saldo do Usuario 2.");
//    }
//
//    public void accountMovement() throws IOException {
//        Assert.assertEquals(accountScreenPageObject.balanceAvailableLabel().getText(),
//                RecorderSet.cash(accountScreenPageObject.balanceAvailableLabel(), "dataUser", "2_user", "balanceAvailable"));
//        Assert.assertEquals(accountScreenPageObject.receiveValueLabel().getText(),
//                RecorderSet.cash(accountScreenPageObject.receiveValueLabel(), "dataUser", "2_user", "receiveCash"));
//        Report.logCapture(Status.INFO, "Observer o extrato do Usuario 2.");
//    }
}