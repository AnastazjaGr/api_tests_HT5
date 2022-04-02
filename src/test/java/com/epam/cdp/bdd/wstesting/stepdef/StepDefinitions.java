package com.epam.cdp.bdd.wstesting.stepdef;

import com.epam.cdp.bdd.wstesting.model.heirs.requests.heirs.PutRequestObject;
import com.epam.cdp.bdd.wstesting.model.heirs.responses.heirs.PutResponseObject;
import com.epam.cdp.bdd.wstesting.core.store.RxStore;
import com.epam.cdp.bdd.wstesting.log.Log;
import com.epam.cdp.bdd.wstesting.model.heirs.requests.RqObject;
import com.epam.cdp.bdd.wstesting.model.heirs.requests.heirs.DeleteRequestObject;
import com.epam.cdp.bdd.wstesting.model.heirs.requests.heirs.GetRequestObject;
import com.epam.cdp.bdd.wstesting.model.heirs.requests.heirs.PostRequestObject;
import com.epam.cdp.bdd.wstesting.model.heirs.responses.RsObject;
import com.epam.cdp.bdd.wstesting.model.heirs.responses.heirs.DeleteResponseObject;
import com.epam.cdp.bdd.wstesting.model.heirs.responses.heirs.GetResponseObject;
import com.epam.cdp.bdd.wstesting.model.heirs.responses.heirs.PostResponseObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class StepDefinitions {
    private RxStore rxStore = RxStore.getInstance();

    @When("^user sends \"([^\"]*)\" \"([^\"]*)\" request$")
    public void userSendsRequest(String requestMethod, String rqName) {
        RqObject requestObject = (RqObject) rxStore.getDataFromStore(rqName);
        String rsName = rqName.replace("RQ", "RS");

        RsObject receivedResponse = switch (requestMethod) {
            case "GET" -> new GetResponseObject(rsName, requestObject.sendGetRequest());
            case "POST" -> new PostResponseObject(rsName, requestObject.sendPostRequest());
            case "DELETE" -> new DeleteResponseObject(rsName, requestObject.sendDeleteRequest());
            case "PUT" -> new PutResponseObject(rsName, requestObject.sendPutRequest());
            default -> throw new IllegalStateException("Unexpected value: " + requestMethod);
        };

        rxStore.putDataIntoStore(receivedResponse.getRxName(), receivedResponse);
    }

    @Then("^\"([^\"]*)\" code is \"([^\"]*)\"$")
    public void responseStatusCodeEqualsExpected(String rsName, String statusCode) {
        RsObject actualResponse = (RsObject) rxStore.getDataFromStore(rsName);
        Assert.assertEquals(statusCode, String.valueOf(actualResponse.getStatusCode()),
                "Status code doesn't match expected");
    }

    @Given("user has {string} request with id {string}")
    public void userHasRequestForGettingUserById(String rqName, String userId) {
        GetRequestObject getRequestObject = new GetRequestObject(rqName);
        getRequestObject.createRequestForGettingPetById(userId);
        rxStore.putDataIntoStore(getRequestObject.getRxName(), getRequestObject);
    }

    @And("pet name contains expected {string}")
    public void petNameEquals(String expectedPetName) {
        GetResponseObject actualResponse = (GetResponseObject) rxStore.getDataFromStore("getPetByIdRS");
        Assert.assertEquals(actualResponse.getPetName(), expectedPetName, "Found result doesn't contains pet name");
    }

    @And("pet name contains expected {string} after update")
    public void petNameAfterUpdateEquals(String expectedPetName) {
        PutResponseObject actualResponse = (PutResponseObject) rxStore.getDataFromStore("updatePetRS");
        Assert.assertEquals(actualResponse.getPetName(), expectedPetName, "Found result doesn't contains pet name");
    }

    @Given("user has {string} request with following parameters")
    public void userHasRequestForCreatingPetWithFollowingParameters(String rqName, DataTable usersInfo)  {
        PostRequestObject postRequestObject = new PostRequestObject(rqName);
        String name = usersInfo.cell(1, 0);
        String status = usersInfo.cell(1, 1);
       postRequestObject.createRequestForCreatingPet( name, status);

        rxStore.putDataIntoStore(postRequestObject.getRxName(), postRequestObject);
        Log.info("Pet was created successfully");
    }

    @And("user has {string} request with id from {string} response")
    public void userHasRequestWithIdFromResponse(String rqName, String rsName) {
        PostResponseObject createPetResponse = (PostResponseObject) rxStore.getDataFromStore(rsName);

        GetRequestObject getRequestObject = new GetRequestObject(rqName);
        getRequestObject.createRequestForGettingPetById(String.valueOf(createPetResponse.getPetId()));

        rxStore.putDataIntoStore(getRequestObject.getRxName(), getRequestObject);
    }

    @And("user has {string} request with id from {string} response and name {string}")
    public void userHasRequestWithIdFromResponse(String rqName,  String rsName, String name) {
        PostResponseObject createPetResponse = (PostResponseObject) rxStore.getDataFromStore(rsName);

        PutRequestObject putRequestObject = new PutRequestObject(rqName);
        putRequestObject.createRequestForUpdatingPetById( createPetResponse.getPetId(), name);

        rxStore.putDataIntoStore(putRequestObject.getRxName(), putRequestObject);
    }

    @Given("user has {string} request by id {string}")
    public void userHasRequestById(String rqName, String petId) {
        DeleteRequestObject deleteRequestObject = new DeleteRequestObject(rqName);
        deleteRequestObject.createRequestForDeletingPet(petId);

        rxStore.putDataIntoStore(deleteRequestObject.getRxName(), deleteRequestObject);
    }
}
