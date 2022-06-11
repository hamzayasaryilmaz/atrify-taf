package api.user;

import api.user.models.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UserApiHelper {
    public static UserList getAllUsers(int page) {
        return when().
                get("/users?page={0}", page).
                as(UserList.class);
    }

    public static UserData getSingleUserById(String id) {
        return when().
                get("/users/{0}", id).
                as(SingleUser.class).getData();
    }

    public static void deleteUserById(String userId) {
        when().
                delete("/users/{0}", userId).
                then().
                statusCode(204);
    }

    public static UserPutResponse updateUser(String userId, UserPost userPost) {
        return given().
                header("Content-type", "application/json").
                body(userPost).
                put("/users/{0}", userId).
                then().
                statusCode(200).extract().response().as(UserPutResponse.class);
    }

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
