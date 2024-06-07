package app.netlify.bugbank.webdrivers;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentTestManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public static WebDriver createInstance(BrowserEnum browser) {
        try {
            switch (browser) {
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver();
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver();
                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    return new EdgeDriver();
                default:
                    String message = "DriverFactory.getInstance() recebeu um argumento invalido";
                    ExtentTestManager.getTest().log(Status.FATAL, message);
                    throw new IllegalArgumentException(message);
            }
        } catch (SessionNotCreatedException e) {
            String message = "Sessao dao criada, versao do driver nao suportada.";
            ExtentTestManager.getTest().log(Status.FATAL, message);
            throw new SessionNotCreatedException(message, e);
        } catch (WebDriverException e) {
            String message = "Nao foi possivel encontrar o binario do driver.";
            ExtentTestManager.getTest().log(Status.FATAL, message);
            throw new WebDriverException(message, e);
        }
    }
}
