 package app.netlify.bugbank.steps;

 import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
 import app.netlify.bugbank.utils.ElementDataUtils;
 import app.netlify.bugbank.utils.Report;
 import app.netlify.bugbank.validations.Validation;
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
         bankStatement();
         checkBalanceAvailability();
         amountToReceive();
         validation.accountMovement();
         signOut();
     }

     private void bankStatement() {
         Report.log(Status.INFO, "Movimento bancária do usuario");
         accountScreenPageObject.balanceStatementButton().click();
     }

     private void checkBalanceAvailability() {
         ElementDataUtils.extractAndStore(accountScreenPageObject.balanceAvailableLabel(), "dataUser", "secondUser", "updateAccountBalance");
     }

     private void amountToReceive() {
         ElementDataUtils.extractAndStore(accountScreenPageObject.amountToReceiveLabel(), "dataUser", "secondUser", "amountToReceive");
     }

     private void signOut() {
         if (accountScreenPageObject.exitAccountButton().isDisplayed()) {
             accountScreenPageObject.exitAccountButton().click();
             Report.logCapture(Status.PASS, "O segundo usuario saiu da conta com sucesso.");
         } else {
             Report.logCapture(Status.FAIL, "Não saiu da conta.");
         }
     }
 }