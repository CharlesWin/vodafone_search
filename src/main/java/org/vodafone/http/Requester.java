package org.vodafone.http;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Requester {
    private Response sendRequest(RequestSpecification requestSpecification, Method method, String path) {
        RequestSpecification specification = requestSpecification == null ? requestSpecification.given() : given().spec(requestSpecification);
        return specification.when().log().all().request(method, path).then().extract().response();
    }

    public Response sendGetRequest(RequestSpecification requestSpecification, String path) {
        return sendRequest(requestSpecification, Method.GET, path);
    }
}
