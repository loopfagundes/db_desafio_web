package app.netlify.bugbank.utils;

import app.netlify.bugbank.webdrivers.BrowserEnum;
import app.netlify.bugbank.webdrivers.DriverFactory;
import app.netlify.bugbank.webdrivers.DriverManager;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners({ExtentITestListenerClassAdapter.class, Report.class})
public class BaseTest {

    @BeforeTest
    public void setUp() {
        WebDriver driver = DriverFactory.createInstance(BrowserEnum.CHROME);
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
        driver.get(Property.get("url"));
    }

    @AfterTest
    public void tearDown() {
        Report.log(Status.INFO, "Encerro a sessão.");
        DriverManager.quitDriver();
    }
}