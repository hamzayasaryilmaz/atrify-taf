package base;

import helpers.PropertiesHelper;
import helpers.TestResultDbHelper;
import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.sql.SQLException;

public class TestBase {
    @BeforeSuite
    public void beforeSuite() {
        PropertiesHelper.loadPropertiesFile("config.properties");
        RestAssured.baseURI = PropertiesHelper.prop.getProperty("application.baseURI");
        RestAssured.basePath = PropertiesHelper.prop.getProperty("application.basePath");
    }

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws SQLException {
        TestResultDbHelper.recordTestResult(result);
    }
}
