package config;

import io.restassured.RestAssured;

import java.io.InputStream;
import java.util.Properties;

public class BaseConfig {

    protected Properties properties;
    public static final String BASE_URL;

    static {
        BASE_URL = new BaseConfig().getProperty("base.url");
        RestAssured.baseURI = BASE_URL;
    }

    public BaseConfig() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
