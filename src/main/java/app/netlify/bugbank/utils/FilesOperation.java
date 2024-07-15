package app.netlify.bugbank.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class FilesOperation {

    private static final String DIR_PATH_PROPERTIES = System.getProperty("user.dir")
            + File.separator
            + "src"
            + File.separator
            + "test"
            + File.separator
            + "resources"
            + File.separator;

    public static Properties getProperties(String nameFile, String name) throws IOException {

        InputStream inputStream = null;
        Properties prop = new Properties();

        try {
            File file = new File(DIR_PATH_PROPERTIES + nameFile + "/" + name + ".properties");
            inputStream = Files.newInputStream(file.toPath());
            prop.load(inputStream);
            return prop;
        } catch (Exception e) {
            LoggerFactory.log_INFO("Não carregou o arquivo" + e.getMessage());
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
        return prop;
    }

    public static void setProperty(String nameFile, String nameProp, String key, String value) throws IOException {
        Properties properties = getProperties(nameFile, nameProp);
        properties.setProperty(key, value);
        saveProperties(nameFile, nameProp, properties);
    }

    private static void saveProperties(String nameFile, String name, Properties properties) throws IOException {
        OutputStream outputStream = null;

        try {
            File file = new File(DIR_PATH_PROPERTIES + nameFile + "/" + name + ".properties");
            outputStream = Files.newOutputStream(file.toPath());
            properties.store(outputStream, null);
        } catch (Exception e) {
            LoggerFactory.log_INFO("Não foi possível salvar as propriedades: " + e.getMessage());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}