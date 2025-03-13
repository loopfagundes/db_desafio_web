package app.netlify.bugbank.steps;

import app.netlify.bugbank.dto.UserDataDTO;
import app.netlify.bugbank.dto.UserModelDTO;
import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.utils.ElementDataUtils;
import app.netlify.bugbank.utils.Report;
import app.netlify.bugbank.validations.Validation;
import app.netlify.bugbank.widgets.Element;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

public class LoginStep {
    private final AccountScreenPageObject accountScreenPageObject;
    private final Validation validation;

    public LoginStep(WebDriver driver) {
        accountScreenPageObject = new AccountScreenPageObject(driver);
        validation = new Validation(driver);
    }

    private void signIn(UserModelDTO user) {
        Report.logCapture(Status.INFO, "Realizado login com o usuario: " + user.getEmail());
        accountScreenPageObject.emailTextField().sendKeys(user.getEmail());
        accountScreenPageObject.passwordTextField().sendKeys(user.getPassword());
        Element.click(accountScreenPageObject.accessAccountButton());
    }

    public void loginAsFirstUser() {
        signIn(UserDataDTO.firstUserData());
        storeUserBalance("firstUser");
        validation.firstUserAccount(UserDataDTO.firstUserData());
        validation.checkStoredBalanceForFirstUser();
    }

    public void loginAsSecondUser() {
        signIn(UserDataDTO.secondUserData());
        storeUserBalance("secondUser");
        validation.validateSecondUserAccount(UserDataDTO.secondUserData());
        validation.checkStoredBalanceForSecondUser();
    }

    private void storeUserBalance(String fileName) {
        ElementDataUtils.extractAndStore(accountScreenPageObject.balanceUserLabel(), "dataUser", fileName, "accountBalance");
    }
}