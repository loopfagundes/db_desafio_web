package app.netlify.bugbank.utils;

import app.netlify.bugbank.webdrivers.DriverManager;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.service.ExtentTestManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshot {
    public static Media capture() {
        String base64image = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);

        try {
            return MediaEntityBuilder.createScreenCaptureFromBase64String(base64image).build();
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL, "Não foi possível gerar uma evidência!");
        }
        return null;
    }
}