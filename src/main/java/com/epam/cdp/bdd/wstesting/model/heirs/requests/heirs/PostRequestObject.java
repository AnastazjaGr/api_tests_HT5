package com.epam.cdp.bdd.wstesting.model.heirs.requests.heirs;

import com.epam.cdp.bdd.wstesting.entities.Pet;
import com.epam.cdp.bdd.wstesting.log.Log;
import com.epam.cdp.bdd.wstesting.model.heirs.requests.RqObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostRequestObject extends RqObject {
    public PostRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForCreatingPet(String name, String status) {
        setBaseUri();
        setCommonParams();
        String json = createPetAsJson(name, status);
        Log.info("createRequestForCreatingPet JSON: " + json);
        requestSpecification.body(json);
    }

    private String createPetAsJson(String name, String status) {
        String json = "{}";
        Pet pet = new Pet()
                .setName(name)
                .setStatus(status);
        try {
            json = new ObjectMapper().writeValueAsString(pet);
        } catch (JsonProcessingException er) {
            Log.error("createPetAsJson Incorrect JSON: " + er);
        }

        return json;
    }
}