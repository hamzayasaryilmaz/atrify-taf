package api.user;

import api.user.models.*;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UserApiHelper {
    @Step("Get all users")
    public static UserList getAllUsers(int page) {
        return when().
                get("/users?page={0}", page).
                as(UserList.class);
    }

    @Step("Get single user by id: {0}")
    public static UserData getSingleUserById(String id) {
        return when().
                get("/users/{0}", id).
                as(SingleUser.class).getData();
    }

    @Step("Delete user by id: {0}")
    public static void deleteUserById(String userId) {
        when().
                delete("/users/{0}", userId).
                then().
                statusCode(204);
    }

    @Step("Update user by id: {0} to {1}")
    public static UserPutResponse updateUser(String userId, UserPost userPost) {
        return given().
                header("Content-type", "application/json").
                body(userPost).
                put("/users/{0}", userId).
                then().
                statusCode(200).extract().response().as(UserPutResponse.class);
    }

    @Step("Create user as: {0}")
    public static UserPostResponse createUser(UserPost userPost) {
        return given().
                header("Content-type", "application/json").
                body(userPost).
                post("/users").
                then().
                statusCode(201).
                extract().
                response().
                as(UserPostResponse.class);
    }
}
