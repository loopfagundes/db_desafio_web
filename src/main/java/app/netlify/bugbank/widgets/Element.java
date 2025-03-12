package app.netlify.bugbank.widgets;

import app.netlify.bugbank.utils.Report;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.testng.Assert;

public class Element {
    private WebDriver driver = null;

    public static void click(WebElement locator) {
        try {
            if (locator.isDisplayed() && locator.isEnabled()) {
                locator.click();
                Report.log(Status.PASS, "O botão " + locator.getText() + " recebeu um clique.");
            } else {
                Report.logCapture(Status.FAIL, "O botão " + locator.getText() + " não recebeu um clique.");
            }
        } catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException |
                 TimeoutException e) {
            throw new RuntimeException("Erro na validação do elemento.", e);
        }
    }

    public void jsClick(WebDriver driver, WebElement locator) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", locator);
        } catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException |
                 TimeoutException e) {
            throw new RuntimeException("Erro na validação do elemento.", e);
        }
    }

    public static void assertEquals(WebElement locator, String expected, String message) {
        try {
            if (locator.isDisplayed()) {
                String actualText = locator.getText().trim();
                Assert.assertEquals(actualText, expected, message);
            }
        } catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException |
                 TimeoutException e) {
            throw new RuntimeException("Erro na validação do elemento.", e);
        }
    }
}