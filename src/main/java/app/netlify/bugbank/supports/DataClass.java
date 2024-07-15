package app.netlify.bugbank.supports;

import app.netlify.bugbank.utils.FilesOperation;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.Iterator;

public class DataClass {
    private final FilesOperation filesOperation = new FilesOperation();

    private static final String csvFile = "src" + File.separator
                    + "test" + File.separator
                    + "resources" + File.separator
                    + "dataUser" + File.separator
                    + "data.csv";

    @DataProvider(name = "data")
    public Iterator<Object[]> loginCsv() {
        return filesOperation.parseCsvData(csvFile);
    }
}