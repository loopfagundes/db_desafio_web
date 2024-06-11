package app.netlify.bugbank.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Action {
    private final WebDriver driver;
    private final Actions actions;

    public Action(WebDriver _driver) {
        driver = _driver;
        actions = new Actions(_driver);
    }

    public Actions actions(WebElement element) {
        actions.click(element);
        return actions;
    }
}