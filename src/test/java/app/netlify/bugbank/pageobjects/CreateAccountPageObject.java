package app.netlify.bugbank.pageobjects;

import app.netlify.bugbank.widgets.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateAccountPageObject {
    private final WebDriver driver;

    public CreateAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public Element registerButton() {
        return new Element(this.driver, By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[3]/button[2]"));
    }

    public Element registerEmailTextField() {
        return new Element(this.driver, By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/input"));
    }

    public Element nameUserTextField() {
        return new Element(this.driver, By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[3]/input"));
    }

    public Element registerPasswordTextField() {
        return new Element(this.driver, By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[4]/div/input"));
    }

    public Element confirmationPassword() {
        return new Element(this.driver, By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[5]/div/input"));
    }

    public Element registerAccountButton() {
        return new Element(this.driver, By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/button"));
    }

    public Element successCloseButton() {
        return new Element(this.driver, By.id("btnCloseModal"));
    }
}