package app.netlify.bugbank.webdrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static boolean isHeadless;

    public static WebDriver createInstance(BrowserEnum browser) {
        String headless = System.getProperty("headless", "false").toLowerCase();
        if (headless.equals("true") || headless.equals("false")) {
            isHeadless = Boolean.parseBoolean(headless);
        } else {
            throw new IllegalArgumentException(
                    "O parâmetro 'headless' aceita apenas valores booleanos: true ou false.");
        }

        try {
            switch (browser) {
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = getFirefoxOptions();
                    return new FirefoxDriver(firefoxOptions);
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = getChromeOptions();
                    return new ChromeDriver(chromeOptions);
                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = getEdgeOptions();
                    return new EdgeDriver(edgeOptions);
                default:
                    throw new IllegalArgumentException("Navegador inválido:" + browser);
            }
        } catch (SessionNotCreatedException e) {
            throw new SessionNotCreatedException("Sessão não criada, versão de driver não suportada.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("Não foi possível encontrar o binário do driver.", e);
        }
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--disable-infobars");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--ignored-certificates-errors");
        if (isHeadless) {
            firefoxOptions.addArguments("--headless");
        }
        return firefoxOptions;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--ignored-certificates-errors");
        if (isHeadless) {
            chromeOptions.addArguments("--headless");
        }
        return chromeOptions;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--no-sandbox");
        edgeOptions.addArguments("--disable-dev-shm-usage");
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--disable-infobars");
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--remote-allow-origins=*");
        edgeOptions.addArguments("--ignored-certificates-errors");
        if (isHeadless) {
            edgeOptions.addArguments("--headless");
        }
        return edgeOptions;
    }
}