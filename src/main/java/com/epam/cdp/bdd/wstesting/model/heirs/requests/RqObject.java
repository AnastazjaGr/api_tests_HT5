package com.epam.cdp.bdd.wstesting.model.heirs.requests;

import com.epam.cdp.bdd.wstesting.model.RxObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RqObject extends RxObject {
    private static final String BASE_URI = "https://petstore.swagger.io/v2/pet";

    protected RequestSpecification requestSpecification;

    public RqObject(String rqName) {
        super(rqName);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        this.requestSpecification = RestAssured.given();
    }

    protected void setBaseUri() {
        setBaseUri(BASE_URI);
    }

    protected void setBaseUri(String uri) {
        requestSpecification.baseUri(uri);
    }

    protected void setCommonParams() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        requestSpecification.headers(headers);
    }

    public Response sendGetRequest() {
        return requestSpecification.get();
    }

    public Response sendPostRequest() {
        return requestSpecification.post();
    }

    public Response sendDeleteRequest() {
        return requestSpecification.delete();
    }

    public Response sendPutRequest() {
        return requestSpecification.put();
    }
}