package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties prop = new Properties();

    static {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("config.properties not found on classpath");
            }
            prop.load(inputStream);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static String getSetting(String envName, String propertyName, String defaultValue) {
        String envValue = System.getenv(envName);
        if (!isBlank(envValue)) {
            return envValue;
        }

        String propertyValue = getProperty(propertyName);
        if (!isBlank(propertyValue)) {
            return propertyValue;
        }

        return defaultValue;
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
