package apiClient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;

import static org.apache.http.HttpStatus.SC_OK;

public class APIClient {
    private Logger logger;

    public APIClient(Logger logger){
        this.logger = logger;
    }
    public Response getRequest(String endpoint) {
        Response resp = RestAssured.given()
                .filter(new AllureRestAssured())
                .when()
                .get(endpoint);
        resp.then().statusCode(SC_OK);
        logResponse(resp);
        return resp;
    }

    public Response postRequest(String endpoint, Object requestBody) {
        Response resp = RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType("application/json")
                .body(requestBody)
                .post(endpoint);
        resp.then().statusCode(SC_OK);
        return resp;
    }

    public Response putRequest(String endpoint, Object requestBody) {
        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .put(endpoint);
        resp.then().statusCode(SC_OK);
        return resp;
    }

    public Response deleteRequest(String endpoint) {
        Response resp = RestAssured.delete(endpoint);
        resp.then().statusCode(SC_OK);
        return resp;
    }

    private void logResponse(Response response) {
        logger.debug("Response Status: {}", response.getStatusLine());
        logger.debug("Response Body: {}", response.getBody().asString());
        logger.debug("Response Headers: {}", response.getHeaders().toString());
    }
}


