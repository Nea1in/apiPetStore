package config;

import io.restassured.RestAssured;

public class BaseConfig {

    private static final PropertiesReader propertiesReader = new PropertiesReader("config.properties");
    public static final String BASE_URL = propertiesReader.getProperty("base.url");

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public static String getProperty(String key) {
        return propertiesReader.getProperty(key);
    }
}