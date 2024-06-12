package app.netlify.bugbank.supports;

import app.netlify.bugbank.utils.FilesOperation;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class RecorderSet {

    public static void recordNumbersFirstUser(WebElement element) throws IOException {
        String accountNumber = element.getText();
        String[] numberSeparator = accountNumber.split("-");
        String justNumber = numberSeparator[0].replaceAll("[^0-9]", "");
        String accountDigit = numberSeparator[1].replaceAll("[^0-9]", "");
        FilesOperation.setProperty("firstUser", "justNumber", justNumber);
        FilesOperation.setProperty("firstUser", "accountDigit", accountDigit);
    }

    public static void recordNumbersSecondUser(WebElement element) throws IOException {
        String accountNumber = element.getText();
        String[] numberSeparator = accountNumber.split("-");
        String justNumber = numberSeparator[0].replaceAll("[^0-9]", "");
        String accountDigit = numberSeparator[1].replaceAll("[^0-9]", "");
        FilesOperation.setProperty("secondUser", "justNumber", justNumber);
        FilesOperation.setProperty("secondUser", "accountDigit", accountDigit);
    }

    public static String recordTest(WebElement element) {
        return getString(element);
    }

    private static String getString(WebElement element) {
        String allSentences = element.getText();
        String ignoreNumbers = allSentences.replaceAll("[0-9-]", "");
        return ignoreNumbers;
    }
}