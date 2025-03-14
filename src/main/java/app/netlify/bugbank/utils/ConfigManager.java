package app.netlify.bugbank.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class ConfigManager {
    private static final String DEFAULT_ENV_FILE = "configuration/url";
    private static final String ENV_VARIABLE = "env";

    public static String get(String key) {
        String filePath = getEnvironmentFile() + ".properties";

        try (InputStream propertyFile = ConfigManager.class.getClassLoader().getResourceAsStream(filePath)) {
            if (propertyFile == null) {
                throw new IllegalArgumentException("Arquivo de propriedades não encontrado: " + filePath);
            }
            Properties properties = new Properties();
            properties.load(propertyFile);
            return properties.getProperty(key);
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao carregar o arquivo: " + filePath, e);
        }
    }

    private static String getEnvironmentFile() {
        String env = System.getProperty(ENV_VARIABLE);
        return Objects.nonNull(env) ? env : DEFAULT_ENV_FILE;
    }
}