package com.atrify.taf;

import api.user.UserApiHelper;
import api.user.UserSample;
import api.user.models.*;
import base.TestBase;
import db.models.TestResult;
import helpers.TestResultDbHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTests extends TestBase {
    @Test
    public void get_all_users_test() {
        List<UserData> userList = UserApiHelper.getAllUsers(1).getData();
        System.out.println(userList.size());
//        Assert.fail("");
    }

    @Test
    public void database_connection_test() {
        List<TestResult> testResults = TestResultDbHelper.getTestResults();
        System.out.println(testResults);
//        Assert.fail("");
    }

    @Test
    public void database_connection_test2() {
        List<TestResult> testResults = TestResultDbHelper.getTestResults();
        System.out.println(testResults);
//        Assert.fail("");
    }

    @Test
    public void database_connection_test3() {
        List<TestResult> testResults = TestResultDbHelper.getTestResults();
        System.out.println(testResults);
//        Assert.fail("");
    }

    @Test
    public void get_single_user_test() {
        UserData user = UserApiHelper.getSingleUserById("2");
//        Assert.fail("");
    }

    @Test
    public void create_a_user_test() {
        UserPostResponse user = UserApiHelper.createUser(UserSample.getDefaultUser());
        // Since it is not a real database, it will not return any record with the id from newly created user.
        UserData singleUserById = UserApiHelper.getSingleUserById(user.getId());
//        Assert.fail("");
    }

    @Test
    public void update_a_user_test() {
        var defaultUser = UserSample.getDefaultUser();
        UserPutResponse updatedUser = UserApiHelper.updateUser("2", defaultUser);
        System.out.println(updatedUser);
//        Assert.fail("");
    }

    @Test
    public void delete_a_user_test() {
        UserApiHelper.deleteUserById("2");
//        Assert.fail("");
    }
}
