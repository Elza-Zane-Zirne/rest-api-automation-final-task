package in.co.gorest.api.serenitySteps;

import in.co.gorest.api.requestBodies.UserRequestBody;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

public class UserSteps {

    /**
     * Method to create a new user
     */

    @Step
    public void createNewUser(String email, String name, String gender, String status) throws Exception {
        CommonSteps.sendRequestWithJsonBody("POST", "/public/v2/users",
                new UserRequestBody().getCreateUser(email, name, gender, status));
    }

    /**
     * Method to delete created user
     */

    @Step
    public void deleteCreatedUser() throws Exception{
        CommonSteps.sendRequest("DELETE", "/public/v2/users/" + Serenity.sessionVariableCalled("userId"));
    }

    /**
     * Method to delete user by id
     */

    @Step
    public void deleteUserById(String id) throws Exception{
        CommonSteps.sendRequest("DELETE", "/public/v2/users/" +id);
    }

    /**
     * Method to get a list of all users
     */

    @Step
    public void updateUserWithEmailAndNameAndStatus(String newEmail, String newName, String newStatus) throws Exception{
        CommonSteps.sendRequestWithJsonBody("PATCH", "/public/v2/users/" + Serenity.sessionVariableCalled("userId"),
                new UserRequestBody().getUpdateUserWithEmailAndNameAndStatus(newEmail, newName, newStatus));
    }

    @Step
    public void getUserList() throws Exception{
        CommonSteps.sendRequest("GET", "/public/v2/users/");
    }

}
