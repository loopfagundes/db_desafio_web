package app.netlify.bugbank.supports;

import app.netlify.bugbank.utils.FilesOperation;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class RecorderSet {

    public static void ignoreTheLetters(WebElement element, String nameProp, String numberAccount, String digit) throws IOException {
        String accountNumber = element.getText();
        String[] numberSeparator = accountNumber.split("-");
        String justNumber = numberSeparator[0].replaceAll("[^0-9]", "");
        String accountDigit = numberSeparator[1].replaceAll("[^0-9]", "");
        FilesOperation.setProperty(nameProp, numberAccount, justNumber);
        FilesOperation.setProperty(nameProp, digit, accountDigit);
    }

    public static String getString(WebElement element) {
        return toReplaceAll(element);
    }

    private static String toReplaceAll(WebElement element) {
        String allSentences = element.getText();
        String ignoreNumbers = allSentences.replaceAll("[0-9-]", "");
        return ignoreNumbers;
    }
}