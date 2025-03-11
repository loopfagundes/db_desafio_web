package app.netlify.bugbank.utils;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;

public class ElementDataUtils {
    private static final String DEFALUT_DIRECTORY = "main";


    public static String toReplaceAll(WebElement element) {
        String ignoreNumbers = element.getText();
        return ignoreNumbers.replaceAll("[0-9-]", "");
    }

    public static void extractAccountDetails(WebElement element, String nameProp, String numberAccount, String digit) {
        String accountNumber = element.getText();
        String[] numberSeparator = accountNumber.split("-");
        String number = numberSeparator[0].replaceAll("[^0-9]", "");
        String numberDigit = numberSeparator[1].replaceAll("[^0-9]", "");
        PropertiesManager.setProperty(DEFALUT_DIRECTORY, "dataUser", nameProp, numberAccount, number);
        PropertiesManager.setProperty(DEFALUT_DIRECTORY, "dataUser", nameProp, digit, numberDigit);
    }

    public static void fakeValue(WebElement element, String nameFolder, String fileName, String key) {
        if (element == null) {
            throw new IllegalArgumentException("O elemento WebElement não pode ser null.");
        }
        int fakeCash = Faker.instance().number().numberBetween(0, 999);
        int fakeCent = Faker.instance().number().numberBetween(0, 99);
        String fakeValue = fakeCash + "." + fakeCent;
        element.sendKeys(fakeValue);
        PropertiesManager.setProperty(DEFALUT_DIRECTORY, nameFolder, fileName, key, fakeValue);
    }

    public static void cash(WebElement element, String nameFolder, String fileName, String key) {
        if (element == null) {
            throw new IllegalArgumentException("O elemento WebElement não pode ser null.");
        }
        String extractText = element.getText();
        PropertiesManager.setProperty(DEFALUT_DIRECTORY, nameFolder, fileName, key, extractText);
    }
}