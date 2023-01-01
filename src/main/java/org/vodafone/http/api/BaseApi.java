package org.vodafone.http.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public abstract class BaseApi<T extends BaseApi<T>> {
    protected RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

    protected void setJsonBody(Object body) {
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setBody(body);
    }

    protected T addQueryParams(Map<String, ?> queryParams) {
        if (queryParams != null && !queryParams.isEmpty()) {
            requestSpecBuilder.addQueryParams(queryParams);
        }
        return instance();
    }

    protected RequestSpecification buildRequestSpecifications() {
        return this.requestSpecBuilder.build();
    }

    protected abstract T instance();
}
