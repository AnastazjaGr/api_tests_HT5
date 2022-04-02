package com.epam.cdp.bdd.wstesting.model.heirs.responses;

import com.epam.cdp.bdd.wstesting.model.*;
import io.restassured.response.Response;

public class RsObject extends RxObject {
    protected Response response;

    public RsObject(String rsName, Response response) {
        super(rsName);
        this.response = response;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }
}
