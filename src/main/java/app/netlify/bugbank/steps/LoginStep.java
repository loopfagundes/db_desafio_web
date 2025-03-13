package app.netlify.bugbank.steps;

import app.netlify.bugbank.dto.UserDataDTO;
import app.netlify.bugbank.dto.UserModelDTO;
import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import app.netlify.bugbank.utils.ElementDataUtils;
import app.netlify.bugbank.validations.Validation;
import app.netlify.bugbank.widgets.Element;

import org.openqa.selenium.WebDriver;

public class LoginStep {
    private final AccountScreenPageObject accountScreenPageObject;
    private final Validation validation;

    public LoginStep(WebDriver driver) {
        accountScreenPageObject = new AccountScreenPageObject(driver);
        validation = new Validation(driver);
    }

    private void performLogin(UserModelDTO user) {
        accountScreenPageObject.emailTextField().sendKeys(user.getEmail());
        accountScreenPageObject.passwordTextField().sendKeys(user.getPassword());
        Element.click(accountScreenPageObject.accessAccountButton());
    }

    public void performUserLoginFirst() {
        performLogin(UserDataDTO.firstUserData());
        extractBalance("firstUser");
        validation.firstUserAccount(UserDataDTO.firstUserData());
        validation.checkStoredBalanceForFirstUser();
    }

    public void performUserLoginSecond() {
        performLogin(UserDataDTO.secondUserData());
        extractBalance("secondUser");
        validation.validateSecondUserAccount(UserDataDTO.secondUserData());
        validation.checkStoredBalanceForSecondUser();
    }

    private void extractBalance(String fileName) {
        ElementDataUtils.extractAndStore(accountScreenPageObject.balanceUserLabel(), "dataUser", fileName, "accountBalance");
    }
}