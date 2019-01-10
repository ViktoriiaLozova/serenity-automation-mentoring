package serenity.bdd.steps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.jbehave.core.model.ExamplesTable;
import org.junit.Ignore;
import org.yecht.Data;
import serenity.bdd.steps.serenity.EndUserSteps;

import java.util.HashMap;
import java.util.Map;

public class DefinitionSteps {

    @Steps
    EndUserSteps endUser;

    @Given("the user is on the Wikionary home page")
    public void givenTheUserIsOnTheWikionaryHomePage() {
        endUser.is_the_home_page();
    }

    @When("the user looks up the definition of the word '$word'")
    public void whenTheUserLooksUpTheDefinitionOf(String word) {
        endUser.looks_for(word);
    }

    @Then("they should see the definition '$definition'")
    public void thenTheyShouldSeeADefinitionContainingTheWords(String definition) {
        endUser.should_see_definition(definition);
    }

    @When("the user looks up the definition of the word list $word")
    public void whenTheUserLooksUpTheDefinitionOfList(String word) {
        endUser.looks_for(word);
    }

    @Then("they should see the definition list $definition")
    public void thenTheyShouldSeeADefinitionContainingTheWordsList(String definition) {
        endUser.should_see_definition(definition);
    }


    @When("the user looks up the definition of the word in table $wordTable")
    public void whenTheUserLooksUpTheDefinitionInTable(ExamplesTable wordTable) {
        Map<String, String> resultTable = new HashMap<>();
        for (Map<String, String> rows: wordTable.getRows()) {
           String word = rows.get("word");
           String definition = rows.get("definition");
           resultTable.put(word, definition);
        }
        endUser.save_table(resultTable);
    }

    @Then("they should see the definition in table")
    public void thenTheyShouldSeeADefinitionContainingTheWordsInTable() {
       endUser.check_table();
    }

}
