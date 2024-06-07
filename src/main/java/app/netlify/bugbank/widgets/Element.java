package app.netlify.bugbank.widgets;

import app.netlify.bugbank.utils.Screenshot;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Element {
    private final WebDriver _driver;
    private final By byElement;
    private WebDriverWait waitDriver;
    private final Duration duration = Duration.ofSeconds(15);

    public Element(WebDriver driver, By by) {
        _driver = driver;
        byElement = by;
    }

    public Element click() {
        try {
            WebElement element = waitVisibilityAndClickableElement(_driver, byElement);
            element.click();
        }  catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            exceptionMessage(byElement);
        }

        ExtentTestManager.getTest().log(Status.INFO, "O elemento " + byElement.toString() + " recebeu um clique.");
        return this;
    }

    public Element setText(String text) {
        try {
            WebElement element = waitVisibilityAndClickableElement(_driver, byElement);
            element.sendKeys(text);
        }  catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            exceptionMessage(byElement);
        }

        ExtentTestManager.getTest().log(Status.INFO, "O elemento " + byElement.toString() + " recebeu seguinte o texto " + text + ".");
        return this;
    }

    public String getText() {
        try {
            WebElement element = waitVisibilityElement(_driver, byElement);
            return element.getText();
        } catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            exceptionMessage(byElement);
        }

        return null;
    }

    private void exceptionMessage(By by) {
        String message = "O elemento " + by.toString() + " existe DOM e tem um conjunto de recursos oculto.";
        ExtentTestManager.getTest().log(Status.WARNING, message, Screenshot.capture());
        throw new ElementNotInteractableException(message);
    }

    private WebElement waitVisibilityElement(WebDriver driver, By by) {
        return visibilityOfElementLocated(driver, by);
    }

    private WebElement waitVisibilityAndClickableElement(WebDriver driver, By by) {
        WebElement elementVisibility = visibilityOfElementLocated(driver, by);
        return elementToBeClickable(driver, elementVisibility);
    }

    private WebElement visibilityOfElementLocated(WebDriver driver, By by) {
        waitDriver = new WebDriverWait(driver, duration);
        return waitDriver.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private WebElement elementToBeClickable(WebDriver driver, WebElement element) {
        waitDriver = new WebDriverWait(driver, duration);
        return waitDriver.until(ExpectedConditions.elementToBeClickable(element));
    }
}