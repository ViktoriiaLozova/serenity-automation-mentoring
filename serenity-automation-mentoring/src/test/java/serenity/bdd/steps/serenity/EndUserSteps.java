package serenity.bdd.steps.serenity;

import serenity.EnvironmentPropertyLoader;
import serenity.bdd.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;
import serenity.endpoints.PetStoreEndPoint;
import serenity.models.Pet;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    DictionaryPage dictionaryPage;
    PetStoreEndPoint petStoreEndPoint = new PetStoreEndPoint();

    private Map<String, String> table;


    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void should_see_definition(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }

    @Step
    public void save_table(Map<String, String> table) {
        this.table = table;
    }

    @Step
    public void check_table() {
        for (Map.Entry<String, String> checkPair: table.entrySet()){
            looks_for(checkPair.getKey());
            should_see_definition(checkPair.getValue());
        }
    }

    @Step
    public void create_pet(String petName, String status) {
        Pet pet = Pet.createBarsik();
        pet.setName(petName);
        pet.setStatus(status);
        petStoreEndPoint.createPet(pet);
    }

    @Step
    public void verify_response_code(int code) {
        petStoreEndPoint.verifyResponseCodeOk(code);
    }

    @Step
    public void find_by_status(String status) {
        petStoreEndPoint.getPetByStatus(status);
    }

    @Step
    public void delete_by_current_id() {
        petStoreEndPoint.deleteByCurrentId();
    }

    @Step
    public String getProperty(String propertyName) {
        return EnvironmentPropertyLoader.getProperty(propertyName);
    }
}