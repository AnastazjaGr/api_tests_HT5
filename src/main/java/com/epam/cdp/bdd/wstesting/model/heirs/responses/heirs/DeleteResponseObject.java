package com.epam.cdp.bdd.wstesting.model.heirs.responses.heirs;

import com.epam.cdp.bdd.wstesting.model.heirs.responses.RsObject;
import io.restassured.response.Response;

public class DeleteResponseObject extends RsObject {
    public DeleteResponseObject(String rsName, Response response) {
        super(rsName, response);
    }
}
