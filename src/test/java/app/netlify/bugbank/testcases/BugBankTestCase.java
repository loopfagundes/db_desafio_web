package app.netlify.bugbank.testcases;

import app.netlify.bugbank.steps.RegisterAccountStep;
import app.netlify.bugbank.utils.BaseTest;
import app.netlify.bugbank.utils.Property;
import app.netlify.bugbank.webdrivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class BugBankTestCase extends BaseTest {

    @Test(
            description = "Criar duas contas com saldo e realizar uma transferência valor para outra.",
            groups = {"web"}
    )
    public void BugBank() throws IOException {
        WebDriver driver = DriverManager.getDriver();
        driver.get(Property.get("url"));
        RegisterAccountStep register = new RegisterAccountStep(driver);
        register.newUser1();
        driver.navigate().refresh();
        register.newUser2();
        //AccountScreenStep account = new AccountScreenStep(driver);
    }
}