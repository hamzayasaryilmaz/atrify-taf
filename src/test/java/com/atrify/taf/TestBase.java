package com.atrify.taf;

import com.github.javafaker.Faker;
import helpers.PropertiesLoader;
import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Properties;

public class TestBase {
    protected Faker faker;
    protected Properties prop;

    @BeforeClass
    public void beforeClass() {
        faker = new Faker();
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        prop = propertiesLoader.loadPropertiesFile("config.properties");
        prop.forEach((k, v) -> System.out.println(k + ":" + v));

        RestAssured.baseURI = prop.getProperty("application.baseURI");
        RestAssured.basePath = prop.getProperty("application.basePath");
    }

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }
}
