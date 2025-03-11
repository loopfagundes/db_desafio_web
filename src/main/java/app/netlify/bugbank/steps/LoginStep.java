package app.netlify.bugbank.steps;

import app.netlify.bugbank.dto.UserDataDTO;
import app.netlify.bugbank.dto.UserModelDTO;
import app.netlify.bugbank.pageobjects.AccountScreenPageObject;
import org.openqa.selenium.WebDriver;

public class LoginStep {
    private final AccountScreenPageObject accountScreenPageObject;

    public LoginStep(WebDriver driver) {
        accountScreenPageObject = new AccountScreenPageObject(driver);
    }

    private void performLogin(UserModelDTO user) {
        accountScreenPageObject.emailTextField().sendKeys(user.getEmail());
        accountScreenPageObject.passwordTextField().sendKeys(user.getPassword());
        accountScreenPageObject.accessAccountButton().click();
    }

    public void performUserLoginFirst() {
        performLogin(UserDataDTO.firstUserData());
    }

    public void performUserLoginSecond() {
        performLogin(UserDataDTO.secondUserData());
    }
}