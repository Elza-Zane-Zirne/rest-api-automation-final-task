package in.co.gorest.api.gherkinsDefinitions;
import in.co.gorest.api.serenitySteps.CommonSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;
import java.util.List;

/**
 * This class contains definitions that are used in multiple features and are commonly used
 */
public class CommonDefinitions {

    @Given("the user is authorised$")
    public void authorise() throws IOException {
        CommonSteps.setAuthToken();
    }

    @Then("^the user gets status code \"([^\"]*)\"$")
    public void assertStatusCode(int statusCode) {
        CommonSteps.assertStatusCode(statusCode);
    }


    @And("^the user saves response key \"([^\"]*)\" as session variable with name \"([^\"]*)\"$")
    public void saveResponseKeyToSession(String key, String varName) {
        CommonSteps.saveResponseKeyToSession(key, varName);
    }

    @Then ("the following values are present in the body")
    public void checkValuesAndSave(DataTable table){
        List<List<String>> rows = table.asLists(String.class);

        for (List<String> columns : rows) {
            CommonSteps.valueOfPathIs(columns.get(0), columns.get(1));
        }
    }
}
