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
            + File.separator
            + "dataUser"
            + File.separator;

    public static Properties getProperties(String name) throws IOException {

        InputStream inputStream = null;
        Properties prop = new Properties();

        try {
            File file = new File(DIR_PATH_PROPERTIES + name + ".properties");
            inputStream = new FileInputStream(file);
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

    public static void setProperty(String nameProp, String key, String value) throws IOException {
        Properties properties = getProperties(nameProp);
        properties.setProperty(key, value);
        saveProperties(nameProp, properties);
    }

    private static void saveProperties(String name, Properties properties) throws IOException {
        OutputStream outputStream = null;

        try {
            File file = new File(DIR_PATH_PROPERTIES + name + ".properties");
            outputStream = new FileOutputStream(file);
            properties.store(outputStream, null);
        } catch (Exception e) {
            LoggerFactory.log_INFO("Não foi possível salvar as propriedades: " + e.getMessage());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public Iterator<Object[]> parseCsvData(String fileName) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build();
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
            String[] line;
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            while ((line = csvReader.readNext()) != null) {
                data.add(line);
            }
            reader.close();
            csvReader.close();
            return data.iterator();
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }
}