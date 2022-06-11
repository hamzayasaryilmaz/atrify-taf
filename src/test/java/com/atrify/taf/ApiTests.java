package com.atrify.taf;

import api.user.UserApiHelper;
import api.user.UserSample;
import api.user.models.*;
import base.TestBase;
import db.models.TestResult;
import helpers.TestResultDbHelper;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTests extends TestBase {
    @Test
    @Description("getAllUsersTest")
    public void getAllUsersTest() {
        List<UserData> userList = UserApiHelper.getAllUsers(1).getData();
        System.out.println(userList.size());
//        Assert.fail();
    }

    @Test
    @Description("database_connection_test")
    public void database_connection_test() {
        List<TestResult> testResults = TestResultDbHelper.getTestResults();
        System.out.println(testResults);
    }

    @Test
    @Description("database_connection_test2")
    public void database_connection_test2() {
        List<TestResult> testResults = TestResultDbHelper.getTestResults();
        System.out.println(testResults);
    }

    @Test
    @Description("database_connection_test3")
    public void database_connection_test3() {
        List<TestResult> testResults = TestResultDbHelper.getTestResults();
        System.out.println(testResults);
    }

    @Test
    @Description("get_single_user_test")
    public void get_single_user_test() {
        UserData user = UserApiHelper.getSingleUserById("2");
    }

    @Test
    @Description("create_a_user_test")
    public void create_a_user_test() {
        UserPostResponse user = UserApiHelper.createUser(UserSample.getDefaultUser());
        // Since it is not a real database, it will not return any record with the id from newly created user.
        UserData singleUserById = UserApiHelper.getSingleUserById(user.getId());
    }

    @Test
    @Description("update_a_user_test")
    public void update_a_user_test() {
        var defaultUser = UserSample.getDefaultUser();
        UserPutResponse updatedUser = UserApiHelper.updateUser("2", defaultUser);
        System.out.println(updatedUser);
    }

    @Test
    @Description("delete_a_user_test")
    public void delete_a_user_test() {
        UserApiHelper.deleteUserById("2");
    }
}
