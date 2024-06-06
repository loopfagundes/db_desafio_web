package app.netlify.bugbank.utils;

import app.netlify.bugbank.webdrivers.BrowserEnum;
import app.netlify.bugbank.webdrivers.DriverFactory;
import app.netlify.bugbank.webdrivers.DriverManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({ExtentITestListenerClassAdapter.class, Report.class})
public class BaseTest {

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverFactory.createInstance(BrowserEnum.CHROME);
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}