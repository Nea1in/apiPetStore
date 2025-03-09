package config;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private final Properties properties = new Properties();

    public PropertiesReader(String fileName) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found: " + fileName);
            }
            properties.load(input);
        } catch (Exception ex) {
            throw new RuntimeException("Error loading configuration: " + fileName, ex);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}