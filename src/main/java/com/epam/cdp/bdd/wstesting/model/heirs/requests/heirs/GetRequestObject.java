package com.epam.cdp.bdd.wstesting.model.heirs.requests.heirs;

import com.epam.cdp.bdd.wstesting.model.heirs.requests.RqObject;

public class GetRequestObject extends RqObject {
    public GetRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForGettingPetById(String petId) {
        setBaseUri("https://petstore.swagger.io/v2/pet/" + petId);
        setCommonParams();
    }
}
