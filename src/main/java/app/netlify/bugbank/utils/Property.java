package app.netlify.bugbank.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class Property {
    public static String get(String property) {
        String filePath = getEnv() + ".properties";

        try(InputStream propertyFile = Property.class.getClassLoader().getResourceAsStream(filePath)) {
            Properties properties = new Properties();
            properties.load(propertyFile);
            return properties.getProperty(property);
        } catch (IOException e) {
            throw new RuntimeException("Arquivo (" + filePath + ") nao encontrado!", e);
        }
    }

    public static String getEnv() {
        if(Objects.nonNull(System.getProperty(".env"))) {
            return System.getProperty("env");
        }
        return "dev";
    }
}