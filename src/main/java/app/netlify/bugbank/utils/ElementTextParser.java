package app.netlify.bugbank.utils;

import org.openqa.selenium.WebElement;

import java.io.IOException;

public class ElementTextParser {

    public static String toReplaceAll(WebElement element) {
        String ignoreNumbers = element.getText();
        return ignoreNumbers.replaceAll("[0-9-]", "");
    }

    public static void ignoreTheLetters(WebElement element, String nameProp, String numberAccount, String digit) throws IOException {
        String accountNumber = element.getText();
        String[] numberSeparator = accountNumber.split("-");
        String number = numberSeparator[0].replaceAll("[^0-9]", "");
        String numberDigit = numberSeparator[1].replaceAll("[^0-9]", "");
        PropertiesManager.setProperty("main", "dataUser", nameProp, numberAccount, number);
        PropertiesManager.setProperty("main", "dataUser", nameProp, digit, numberDigit);
    }
}