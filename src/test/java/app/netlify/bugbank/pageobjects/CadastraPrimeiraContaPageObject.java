package app.netlify.bugbank.pageobjects;

import app.netlify.bugbank.widgets.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastraPrimeiraContaPageObject {
    private final WebDriver driver;

    public CadastraPrimeiraContaPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public Element registrarButton() {
        return new Element(this.driver, By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[3]/button[2]"));
    }

    public Element emailTextField() {
        return new Element(this.driver, By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/input"));
    }
}