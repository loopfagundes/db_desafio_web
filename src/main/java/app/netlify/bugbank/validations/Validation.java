 package app.netlify.bugbank.validations;

 import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
 import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
 import app.netlify.bugbank.pageobjects.TransferPageObject;
 import app.netlify.bugbank.utils.Report;
 import app.netlify.bugbank.utils.ElementTextParser;
 import com.aventstack.extentreports.Status;
 import org.openqa.selenium.WebDriver;

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
         if (ElementTextParser.toReplaceAll(createAccountPageObject.createdSuccessfullyModalLabel()).equals("A conta  foi criada com sucesso")) {
             Report.logCapture(Status.PASS, "O usuario foi criada com sucesso");
             createAccountPageObject.successCloseModalButton().click();
         } else {
             Report.logCapture(Status.FAIL, "Ocorreu um erro no registro.");
         }
     }

//     public void firstUserAccountPage() {
//         Assert.assertEquals(accountScreenPageObject.helloUserLabel().getText(), "Olá User_1,");
//         Assert.assertEquals(accountScreenPageObject.balanceUserLabel().getText(), "Saldo em conta R$ 1.000,00");
//         Report.logCapture(Status.INFO, "Observer o saldo do Usuario 1.");
//     }
//
//     public void transferCompletedSuccessfully() {
//         Assert.assertEquals(transferPageObject.transferSuccessfullyLabel().getText(), "Transferencia realizada com sucesso");
//     }
//
//     public void remainingBalance() throws IOException {
//         Assert.assertEquals(accountScreenPageObject.balanceUserLabel().getText(),
//                 RecorderSet.cash(accountScreenPageObject.balanceUserLabel(), "dataUser", "1_user", "cashBalance"));
//     }
//
//     public void secondUserAccountPage() throws IOException {
//         Assert.assertEquals(accountScreenPageObject.helloUserLabel().getText(), "Olá User_2,");
//         Assert.assertEquals(accountScreenPageObject.balanceUserLabel().getText(),
//                 RecorderSet.cash(accountScreenPageObject.balanceUserLabel(), "dataUser", "2_user", "cashBalance"));
//         Report.logCapture(Status.INFO, "Observer o saldo do Usuario 2.");
//     }
//
//     public void accountMovement() throws IOException {
//         Assert.assertEquals(accountScreenPageObject.balanceAvailableLabel().getText(),
//                 RecorderSet.cash(accountScreenPageObject.balanceAvailableLabel(), "dataUser", "2_user", "balanceAvailable"));
//         Assert.assertEquals(accountScreenPageObject.receiveValueLabel().getText(),
//                 RecorderSet.cash(accountScreenPageObject.receiveValueLabel(), "dataUser", "2_user", "receiveCash"));
//         Report.logCapture(Status.INFO, "Observer o extrato do Usuario 2.");
//     }
 }