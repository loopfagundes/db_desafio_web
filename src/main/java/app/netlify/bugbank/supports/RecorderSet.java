package app.netlify.bugbank.supports;

import app.netlify.bugbank.utils.FilesOperation;
import com.github.javafaker.Faker;
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

    public static String toReplaceAll(WebElement element) {
        String ignoreNumbers = element.getText();
        return ignoreNumbers.replaceAll("[0-9-]", "");
    }

    public static void fakeValue(WebElement element, String nameProp, String key) throws IOException {
        String fakeCash = Faker.instance().number().digits(Integer.parseInt("3"));
        String fakeCent = Faker.instance().number().digits(Integer.parseInt("2"));
        String fakeValue = fakeCash + "." + fakeCent;
        element.sendKeys(fakeValue);
        FilesOperation.setProperty(nameProp, key, fakeValue);
    }

    public static String cash(WebElement element, String nameProp, String key) throws IOException {
        String value = element.getText();
        FilesOperation.setProperty(nameProp, key, value);
        return value;
    }
}