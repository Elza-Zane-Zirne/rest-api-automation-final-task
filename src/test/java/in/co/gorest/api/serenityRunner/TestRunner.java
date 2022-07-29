package in.co.gorest.api.serenityRunner;

import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class contains settings for execution an @BeforeClass hook
 */

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = {"src/test/resources/features"},
        glue = {"in.co.gorest.api.gherkinsDefinitions"}
)
public class TestRunner {

    private TestRunner() {
    }

    @BeforeClass
    public static void setup() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/java/resources/data.properties"));
        RestAssured.baseURI = prop.getProperty("baseURL");
    }
}
