package com.epam.cdp.bdd.wstesting.model.heirs.requests.heirs;

import com.epam.cdp.bdd.wstesting.model.heirs.requests.RqObject;

public class DeleteRequestObject extends RqObject {
    public DeleteRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForDeletingPet(String petId) {
        setBaseUri("https://petstore.swagger.io/v2/pet/" + petId);
        setCommonParams();
    }
}
