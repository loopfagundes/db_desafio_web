package app.netlify.bugbank.utils;

import org.openqa.selenium.WebElement;

public class TextUtils {

    public static String toReplaceAll(WebElement element) {
        String ignoreNumbers = element.getText();
        return ignoreNumbers.replaceAll("[0-9-]", "");
    }
}