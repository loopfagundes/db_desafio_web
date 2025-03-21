package app.netlify.bugbank.utils;

import app.netlify.bugbank.webdrivers.BrowserEnum;
import app.netlify.bugbank.webdrivers.DriverFactory;
import app.netlify.bugbank.webdrivers.DriverManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners({ExtentITestListenerClassAdapter.class, Report.class})
public class BaseTest {

    @BeforeTest
    public void setUp() {
        DirectoryManager.resetFolder("main", "dataUser");
        WebDriver driver = DriverFactory.createInstance(BrowserEnum.CHROME);
        DriverManager.setDriver(driver);
        driver.get(ConfigManager.get("url"));
    }

    @AfterTest
    public void tearDown() {
       DriverManager.quitDriver();
    }
}