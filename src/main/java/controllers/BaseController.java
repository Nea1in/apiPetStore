package controllers;

import config.BaseConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BaseController extends BaseConfig {

    protected static final String HEADER_CONTENT_TYPE = "Content-Type";
    protected static final String APPLICATION_JSON = "application/json";

    private RequestSpecification buildRequest() {
        return RestAssured.given()
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON)
                .contentType(ContentType.JSON)
                .log().method().request()
                .log().uri()
                .log().body()
                .log().headers();
    }

    protected Response getById(String endpoint) {
        return buildRequest().get(endpoint);
    }

    protected Response post(String endpoint, Object body) {
        return buildRequest().body(body).post(endpoint);
    }

    public Response put(String endpoint, Object body) {
        return buildRequest().body(body).put(endpoint);
    }

    public Response delete(String endpoint) {
        return buildRequest().delete(endpoint);
    }
}
