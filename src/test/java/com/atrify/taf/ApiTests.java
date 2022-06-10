package com.atrify.taf;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ApiTests extends TestBase{
    @Test
    public void get_all_users() {
        when().
                get("/users?page={0}", 2).
                then().
                statusCode(200);
    }

    @Test
    public void get_single_user() {
        when().
                get("/users/{0}", 2).
                then().
                statusCode(200);
    }

    @Test
    public void create_new_user() {
        given().
                header("Content-type", "application/json").
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}").
                post("/users").
                then().
                statusCode(201);
    }

    @Test
    public void update_existing_user() {
        given().
                header("Content-type", "application/json").
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}").
                put("/users/{0}", 2).
                then().
                statusCode(200);
    }

    @Test
    public void delete_a_user() {
        when().
                delete("/users/{0}", 2).
                then().
                statusCode(204);
    }
}
