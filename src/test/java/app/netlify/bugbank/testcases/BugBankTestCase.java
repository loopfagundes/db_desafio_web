package app.netlify.bugbank.testcases;

import app.netlify.bugbank.steps.AccountScreenStep;
import app.netlify.bugbank.steps.RegisterFristUserAccountStep;
import app.netlify.bugbank.steps.RegisterSecondUserAccountStep;
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
        RegisterFristUserAccountStep firstUser = new RegisterFristUserAccountStep(driver);
        RegisterSecondUserAccountStep secondUser = new RegisterSecondUserAccountStep(driver);
        AccountScreenStep account = new AccountScreenStep(driver);
        firstUser.indexPage(
                Property.get("emailFirst"),
                Property.get("userFirst"),
                Property.get("passwordUserFirst")
        );
        driver.navigate().refresh();
        secondUser.indexPage(
                Property.get("emailSecond"),
                Property.get("userSecond"),
                Property.get("passwordUserSecond")
        );
        account.userAccountFirst(
                Property.get("emailFirst"),
                Property.get("passwordUserFirst"),
                Property.get("value"),
                Property.get("description")
        );
    }
}