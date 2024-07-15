package app.netlify.bugbank.supports;

import app.netlify.bugbank.utils.FilesOperation;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class RecorderSet {

    public static void ignoreTheLetters(WebElement element, String nameFile, String nameProp, String numberAccount, String digit) throws IOException {
        String accountNumber = element.getText();
        String[] numberSeparator = accountNumber.split("-");
        String number = numberSeparator[0].replaceAll("[^0-9]", "");
        String numberDigit = numberSeparator[1].replaceAll("[^0-9]", "");
        FilesOperation.setProperty(nameFile, nameProp, numberAccount, number);
        FilesOperation.setProperty(nameFile, nameProp, digit, numberDigit);
    }

    public static String toReplaceAll(WebElement element) {
        String ignoreNumbers = element.getText();
        return ignoreNumbers.replaceAll("[0-9-]", "");
    }

    public static void fakeValue(WebElement element, String nameFile, String nameProp, String key) throws IOException {
        String fakeCash = Faker.instance().number().digits(Integer.parseInt("3"));
        String fakeCent = Faker.instance().number().digits(Integer.parseInt("2"));
        String fakeValue = fakeCash + "." + fakeCent;
        element.sendKeys(fakeValue);
        FilesOperation.setProperty(nameFile, nameProp, key, fakeValue);
    }

    public static String cash(WebElement element, String nameFile, String nameProp, String key) throws IOException {
        String value = element.getText();
        FilesOperation.setProperty(nameFile, nameProp, key, value);
        return value;
    }
}