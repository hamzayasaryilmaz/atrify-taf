package api.user;

import api.user.models.*;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UserApiHelper {
    public static UserList getAllUsers() {
        return getAllUsers(0, 12);
    }

    @Step("Get all users with page #{0} and user per page: {1}")
    public static UserList getAllUsers(int page, int perPage) {
        return given().
                queryParam("page", page).
                queryParam("perPage", perPage).
                when().
                get("/users").
                as(UserList.class);
    }

    @Step("Get single user by id: {0}")
    public static Response getSingleUserById(String id) {
        return given().
                pathParam("id", id).
                when().
                get("/users/{id}").thenReturn();
    }

    @Step("Delete user by id: {0}")
    public static Response deleteUserById(String userId) {
        return when().
                delete("/users/{0}", userId).
                thenReturn();
    }

    @Step("Update user by id: {0} to {1}")
    public static Response updateUser(String userId, UserPost userPost) {
        return given().pathParam("id", "").
                header("Content-type", "application/json").
                body(userPost).
                put("/users/{id}").thenReturn();
    }

    @Step("Create user as: {0}")
    public static Response createUser(UserPost userPost) {
        return given().
                header("Content-type", "application/json").
                body(userPost).
                post("/users").thenReturn();
    }
}
