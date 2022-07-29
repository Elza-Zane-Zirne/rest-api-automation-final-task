package in.co.gorest.api.gherkinsDefinitions;
import in.co.gorest.api.serenitySteps.UserSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

/**
 * This class contains user definitions
 */

public class UserDefinitions {

    @Steps
    public UserSteps userSteps;


    @When("^the user deletes user with id \"([^\"]*)\"$")
    public void deleteUserById(String id) throws Exception{
        userSteps.deleteUserById(id);
    }

    @And("^the user deletes created user$")
    public void deleteCreatedUser() throws Exception{
        userSteps.deleteCreatedUser();
    }

    @And("^the user creates a new user with the following values email \"([^\"]*)\", name \"([^\"]*)\", gender \"([^\"]*)\" and status \"([^\"]*)\"$")
    public void createNewUser(String email, String name, String gender, String status) throws Exception {
        userSteps.createNewUser(email, name, gender, status);
    }


    @When ("^the user updates created user with new email \"([^\"]*)\", new name \"([^\"]*)\" and new status \"([^\"]*)\"")
    public void updateUserWithEmailAndNameAndStatus (String newEmail, String newName, String newStatus) throws Exception {
        userSteps.updateUserWithEmailAndNameAndStatus(newEmail, newName, newStatus);
    }


}
