package app.netlify.bugbank.steps;

import app.netlify.bugbank.dto.UserDataDTO;
import app.netlify.bugbank.dto.UserModelDTO;
import app.netlify.bugbank.pageobjects.AccountScreenPageObject;

import org.openqa.selenium.WebDriver;

public class LoginStep {
    private final AccountScreenPageObject accountScreenPageObject;
    private final UserModelDTO user;

    public LoginStep(WebDriver driver) {
        accountScreenPageObject = new AccountScreenPageObject(driver);
        user = UserDataDTO.firstUserData();
    }

    public void performLogin() {
        accountScreenPageObject.emailTextField().sendKeys(user.getEmail());
        accountScreenPageObject.passwordTextField().sendKeys(user.getPassword());
        accountScreenPageObject.accessAccountButton().click();
    }
}