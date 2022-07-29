package in.co.gorest.api.serenitySteps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.decorators.request.RequestSpecificationDecorated;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.json.JSONObject;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static net.serenitybdd.rest.SerenityRest.rest;

import in.co.gorest.api.support.Service;


/**
 * Class contains implementations for the CommonSteps
 */
public class CommonSteps {

    public static final String RESPONSE = "response";
    private static RequestSpecification requestSpecification = rest();
    public static String token = null;


    /**
     * Method to assert received status code
     */
    @Step
    public static void assertStatusCode(int expectedStatusCode) {
        Response res = Serenity.sessionVariableCalled(RESPONSE);

        if (res.getStatusCode() != expectedStatusCode) {
            System.err.println(" Response Body:");
            res.getBody().prettyPrint();
        }

        Assert.assertEquals("status codes don't match," +
                "\nRequest:\nHeaders: " + Serenity.sessionVariableCalled("REQUEST-HEADERS") + "\nMethod: " + Serenity.sessionVariableCalled("REQUEST-METHOD") +
                "\nUrl: " + Serenity.sessionVariableCalled("REQUEST-URL") +
                "\nResponse:\nHeaders: " + res.getHeaders() + "\n ~Name: " + "~" + "~\n", expectedStatusCode, res.getStatusCode());
    }

    /**
     * Method to set auth token
     */
    public static void setAuthToken() throws IOException {
        RequestSpecification requestSpecification1 = rest();
        HashMap<String, String> hashMap = new HashMap();

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/java/resources/data.properties"));

        hashMap.put("Authorization", "Bearer " + prop.getProperty("token"));
        hashMap.put("Content-type", "application/json");

        requestSpecification1.headers(hashMap);
        requestSpecification = requestSpecification1;
    }

    /**
     * Method to create a request
     */

    @Step
    public static void sendRequest(String method, String endpoint) {
        if (token != null) {
            requestSpecification.header("Authorization", "Bearer " + token);
        }

        Serenity.setSessionVariable("REQUEST-HEADERS").to((String) ((RequestSpecificationDecorated) requestSpecification).getHeaders().toString());
        Serenity.setSessionVariable("REQUEST-METHOD").to(method);
        Serenity.setSessionVariable("REQUEST-URL").to(RestAssured.baseURI + endpoint);

        Response response = Service.sendRequest(requestSpecification, method, endpoint)
                .then()
                .extract()
                .response();
        response.prettyPrint();
        Serenity.setSessionVariable(RESPONSE).to(response);
    }

    /**
     * Method to create a request with JSON body
     */

    @Step
    public static void sendRequestWithJsonBody(String method, String endpoint, String body) {
        JSONObject bodyJson = new JSONObject(body);
        Map<String, Object> bodyMap = bodyJson.toMap();

        requestSpecification.body(bodyMap);

        sendRequest(method, endpoint);
    }

    /**
     * Method to save value from response to the session variable
     */

    @Step
    public static void saveResponseKeyToSession(String key, String sessionVar){
        Response response = Serenity.sessionVariableCalled(RESPONSE);
        Assert.assertNotNull(key + " not found in body response", response.getBody().jsonPath().getString(key));
        Serenity.setSessionVariable(sessionVar).to(response.getBody().jsonPath().getString(key));
    }

    /**
     * Method to check the expected and actual value of the path given as param
     */
    @Step
    public static void valueOfPathIs(String path, String expectedValue) {
        Response response = Serenity.sessionVariableCalled(RESPONSE);

        if (expectedValue.equals("-")) {
            //checked that value is not 0
            Assert.assertNotEquals("Expected value is " + response.body().jsonPath().getString(path), 0,
                    response.body().jsonPath().getString(path));
            String testData = "Tax value is " + response.body().jsonPath().getString(path);
            Serenity.recordReportData().withTitle("Tax value").andContents(testData);
        } else {
            Assert.assertEquals("Expected value is: " + expectedValue, expectedValue,
                    response.body().jsonPath().getString(path));
        }
    }



}
