package com.epam.cdp.bdd.wstesting.model.heirs.responses.heirs;

import com.epam.cdp.bdd.wstesting.model.heirs.responses.RsObject;
import io.restassured.response.Response;

import java.util.List;

public class PutResponseObject extends RsObject {
    private static final String RESULT_NAME_LOCATOR = "name";

    public PutResponseObject(String rsName, Response response) {
        super(rsName, response);
    }

    public String getPetName() {
        return response.jsonPath().get(RESULT_NAME_LOCATOR);
    }
}