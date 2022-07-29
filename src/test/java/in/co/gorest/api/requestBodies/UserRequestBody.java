package in.co.gorest.api.requestBodies;

import in.co.gorest.api.util.JsonReader;

/**
 * Class contains User request bodies
 */

public class UserRequestBody {

    private static final String dir = System.getProperty("user.dir");
    public String getCreateUser(String email, String name, String gender, String status) throws Exception {

        String requestBody = JsonReader.readJsonFileAsString(dir + "/src/test/resources/data/createNewUser.json");

        requestBody = requestBody.replace("$EMAIL", email);
        requestBody = requestBody.replace("$NAME", name);
        requestBody = requestBody.replace("$GENDER", gender);
        return requestBody.replace("$STATUS", status);
    }

    public String getUpdateUserWithEmailAndNameAndStatus(String newEmail, String newName, String newStatus) throws Exception {

        String requestBody = JsonReader.readJsonFileAsString(dir + "/src/test/resources/data/updateUserWithEmailAndNameAndStatus.json");
        return requestBody.replace("$EMAIL", newEmail).replace("$NAME", newName).replace("$STATUS", newStatus);

    }
}
