package serenity.bdd.pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpHeaders;
import serenity.Config;
import serenity.models.Pet;

public class PetStorePage {
    private Response response;
    private long currentPetId = -1;

    @Step("create Pet")
    public void createPet(Pet pet) {
        response = given()
                .body(pet)
                .when()
                .post(Config.CREATE_PET)
                .then().extract().response();

        Pet createdPet = response.body().as(Pet.class);
        currentPetId = createdPet.getId();
    }

    @Step("get Pet by Status")
    public void getPetByStatus(String status) {
        response = given()
                .queryParam("status", status)
                .when()
                .get(Config.FIND_BY_STATUS)
                .then().extract().response();
    }

    @Step("delete Pet by current id")
    public void deleteByCurrentId() {
        response = given()
                .when()
                .delete(Config.PET_BY_ID, currentPetId)
                .then().extract().response();
    }

    @Step("verify response code ok")
    public void verifyResponseCodeOk() {
        response.then().statusCode(200);
    }

    private RequestSpecification given() {
        return SerenityRest.given()
                .baseUri(Config.PETSTORE_BASE_URL)
                .header(HttpHeaders.CONTENT_TYPE, "application/json");
    }
}
