package app.netlify.bugbank.supports;

import app.netlify.bugbank.utils.FilesOperation;

import java.io.IOException;

public class RecorderGet {

    public static String justNumber() throws IOException {
        String justNumber = null;
        justNumber = FilesOperation.getProperties("secondUser").getProperty("justNumber");
        return justNumber;
    }

    public static String accountDigit() throws IOException {
        String accountDigit = null;
        accountDigit = FilesOperation.getProperties("secondUser").getProperty("accountDigit");
        return accountDigit;
    }
}