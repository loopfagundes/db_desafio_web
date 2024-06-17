package app.netlify.bugbank.supports;

import app.netlify.bugbank.utils.FilesOperation;

import java.io.IOException;

public class RecorderGet {

    public static String justNumber(String nameProp, String key) throws IOException {
        return FilesOperation.getProperties(nameProp).getProperty(key);
    }

    public static String accountDigit(String nameProp, String key) throws IOException {
        return FilesOperation.getProperties(nameProp).getProperty(key);
    }
}