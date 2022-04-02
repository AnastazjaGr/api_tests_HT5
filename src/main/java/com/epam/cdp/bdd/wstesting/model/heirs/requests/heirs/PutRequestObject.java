package com.epam.cdp.bdd.wstesting.model.heirs.requests.heirs;

import com.epam.cdp.bdd.wstesting.entities.Pet;
import com.epam.cdp.bdd.wstesting.log.Log;
import com.epam.cdp.bdd.wstesting.model.heirs.requests.RqObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PutRequestObject extends RqObject {
    public PutRequestObject(String rqName) {
        super(rqName);
    }

    private String updatePetAsJson(long petId, String name) {

        String json = "{}";
        Pet pet = new Pet()
                .setId(petId)
                .setName(name);
        try {
            json = new ObjectMapper().writeValueAsString(pet);
        } catch (JsonProcessingException er) {
            Log.error("updatePetAsJson Incorrect JSON: " + er);
        }

        return json;
    }

    public void createRequestForUpdatingPetById(long petId, String name) {
        setBaseUri();
        setCommonParams();

        String json = updatePetAsJson(petId, name);
        Log.info("createRequestForUpdatingPetById JSON: " + json);

        requestSpecification.body(json);
    }
}