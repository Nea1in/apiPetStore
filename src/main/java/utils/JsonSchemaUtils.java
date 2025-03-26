package utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import java.io.File;

public class JsonSchemaUtils {
    private static final LoggerUtility logger = new LoggerUtility(JsonSchemaUtils.class);

    public static boolean isValidJsonSchema(Response response, String schemaFilePath) {
        try {
            File schemaFile = new File(schemaFilePath);
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
            logger.info("JSON schema validation passed for schema: {}", schemaFilePath);
            return true;
        } catch (AssertionError e) {
            logger.error("JSON schema validation failed: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            logger.error("Unexpected error during JSON schema validation", e);
            return false;
        }
    }
}

