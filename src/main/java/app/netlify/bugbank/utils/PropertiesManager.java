package app.netlify.bugbank.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesManager {

    private static final String BASE_DIR = System.getProperty("user.dir");

    public static Properties loadProperties(String directory, String nameFolder, String fileName) {
        Path filePath = buildPath(directory, nameFolder, fileName);
        createFileIfNotExists(filePath);
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo: " + filePath, e);
        }
        return properties;
    }

    public static void setProperty(String directory, String nameFolder, String fileName, String key, String value) {
        Properties properties = loadProperties(directory, nameFolder, fileName);
        properties.setProperty(key, value);
        saveProperties(directory, nameFolder, fileName, properties);
    }

    private static void saveProperties(String directory, String nameFolder, String fileName, Properties properties) {
        Path filePath = buildPath(directory, nameFolder, fileName);
        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar as propriedades no arquivo: " + filePath, e);
        }
    }

    private static Path buildPath(String directory, String nameFolder, String fileName) {
        return Paths.get(BASE_DIR, "src", directory, "resources", nameFolder, fileName + ".properties");
    }

    private static void createFileIfNotExists(Path filePath) {
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                LoggerFactory.log_INFO("Arquivo criado automaticamente: " + filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar o arquivo: " + filePath, e);
        }
    }
}