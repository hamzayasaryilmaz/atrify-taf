package base;

import com.google.common.collect.ImmutableMap;
import helpers.PropertiesHelper;
import helpers.TestResultDbHelper;
import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.sql.SQLException;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class TestBase {
    @BeforeSuite
    public void beforeSuite() {
        PropertiesHelper.loadPropertiesFile("config.properties");
        RestAssured.baseURI = PropertiesHelper.prop.getProperty("application.baseURI");
        RestAssured.basePath = PropertiesHelper.prop.getProperty("application.basePath");

        var a = System.getProperty("profileId");
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("base uri", PropertiesHelper.prop.getProperty("application.baseURI"))
                        .put("base path", PropertiesHelper.prop.getProperty("application.basePath"))
                        .put("database name", PropertiesHelper.prop.getProperty("db.name"))
                        .build(), System.getProperty("user.dir") + "/target/allure-results/");
    }

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws SQLException {
        TestResultDbHelper.recordTestResult(result);
    }
}
