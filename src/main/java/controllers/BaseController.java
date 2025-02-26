package controllers;

import config.BaseConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class BaseController extends BaseConfig {

    protected static final String HEADER_CONTENT_TYPE = "Content-Type";
    protected static final String APPLICATION_JSON = "application/json";



    protected Response getById(String path) {
        return RestAssured.given()
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .contentType(ContentType.JSON)
                .when()
                .log().method().request()
                .log().uri()
                .log().headers()
                .get(path);
    }

    protected Response post(String path, Object body) {
        return RestAssured.given()
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .log().method().request()
                .log().uri()
                .log().body()
                .log().headers()
                .post( path);
    }


    public Response put(String endpoint, Object body) {
        return RestAssured.given()
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .put(endpoint);
    }

    public Response delete(String endpoint) {
        return RestAssured.given()
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .contentType(ContentType.JSON)
                .delete(endpoint);
    }
}
