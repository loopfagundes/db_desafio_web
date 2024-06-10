package app.netlify.bugbank.utils;

import java.io.*;
import java.util.Properties;

public class FilesOperation {

    private static final String DIR_PATH_PROPERTIES = System.getProperty("user.dir")
            + File.separator
            + "src"
            + File.separator
            + "test" +
            File.separator
            + "resources"
            + File.separator
            + "properties"
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
            System.out.println("Não carregou o arquivo" + e.getMessage());
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
            System.out.println("Não foi possível salvar as propriedades: " + e.getMessage());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}