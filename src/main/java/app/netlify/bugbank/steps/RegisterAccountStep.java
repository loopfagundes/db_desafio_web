package app.netlify.bugbank.steps;

import app.netlify.bugbank.pageobjects.CreateAccountPageObject;
import app.netlify.bugbank.supports.RecorderGet;
import app.netlify.bugbank.supports.RecorderSet;
import app.netlify.bugbank.utils.JsExecutor;
import app.netlify.bugbank.utils.Report;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegisterAccountStep {
    private final WebDriver driver;
    private final CreateAccountPageObject createAccountPageObject;

    public RegisterAccountStep(WebDriver _driver) {
        driver = _driver;
        createAccountPageObject = new CreateAccountPageObject(_driver);
    }

    public void newUser1() throws IOException {
        register("firstUser", "emailFirst", "userFirst", "passwordUserFirst");
    }

    public void newUser2() throws IOException {
        register("secondUser", "emailSecond", "userSecond", "passwordUserSecond");
    }

    private void register(String dataUser, String emailUser, String nameUser, String passwordUser) throws IOException {
        Report.log(Status.INFO, "Cadastrar uma nova conta do usuario.");
        createAccountPageObject.registerButton().click();
        createAccountPageObject.registerEmailTextField().sendKeys(RecorderGet.getDataUser(dataUser, emailUser));
        createAccountPageObject.nameUserTextField().sendKeys(RecorderGet.getDataUser(dataUser, nameUser));
        createAccountPageObject.registerPasswordTextField().sendKeys(RecorderGet.getDataUser(dataUser, passwordUser));
        createAccountPageObject.confirmationPasswordTextField().sendKeys(RecorderGet.getDataUser(dataUser, passwordUser));
        JsExecutor.click(driver, createAccountPageObject.balanceAccountButton());
        createAccountPageObject.registerAccountButton().click();
        RecorderSet.ignoreTheLetters(createAccountPageObject.createdSuccessfullyModalLabel(), dataUser, "justNumber", "accountDigit");
        createAccountPageObject.successCloseModalButton().click();
    }
}