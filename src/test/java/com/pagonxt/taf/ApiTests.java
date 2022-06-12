package com.pagonxt.taf;

import api.user.UserApiHelper;
import api.user.UserApiValidations;
import api.user.UserSample;
import api.user.models.*;
import base.TestBase;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiTests extends TestBase {
    List<String> createdUsersId = new ArrayList<>();

    @Test
    @Description("Test to get all users and verify if the count of users equal to 6 on a single page")
    public void get_all_users_test() {
        List<UserData> userList = UserApiHelper.getAllUsers(2, 6).getData();
        assertThat(userList.size(), equalTo(6));

    }

    @Test
    @Description("Test to get user with non-existed page like page #3")
    public void get_all_users_with_non_existed_page_test() {
        // 3. users page, is not existed, therefore it should return 0 user.
        List<UserData> userList = UserApiHelper.getAllUsers(3, 6).getData();
        assertThat(userList.size(), equalTo(0));
    }

    @Test
    @Description("Test to calculate all users by looping the pages")
    public void calculate_all_users_in_total_test() {
        UserList userList = UserApiHelper.getAllUsers(0, 6);
        UserApiValidations.checkCalculationOfUserList(userList);
    }

    @Test
    @Description("get_single_user_test")
    public void get_single_user_test() {
        UserData user = UserApiHelper.getSingleUserById("2").as(SingleUser.class).getData();
        System.out.println(user);
    }

    @Test
    @Description("create_a_user_test")
    public void create_a_user_test() {
        var response = UserApiHelper.createUser(UserSample.getDefaultUser().build());
        var user = response.as(UserPostResponse.class);
        assertThat(response.getStatusCode(), is(201));
        createdUsersId.add(user.getId());

        // Since it reqres.in apis CRUD operations do not have any real affect, user will not be actually created
        UserData singleUserById = UserApiHelper.getSingleUserById(user.getId()).as(SingleUser.class).getData();;
        assertThat(singleUserById, is(nullValue()));
    }

    @Test
    @Description("update_a_user_test")
    public void update_a_user_test() {
        var defaultUser = UserSample.getDefaultUser().build();
        Response response = UserApiHelper.updateUser("2", defaultUser);
        UserPutResponse userPutResponse = response.as(UserPutResponse.class);

        assertThat(userPutResponse.getName(), is(equalTo(defaultUser.getName())));
        assertThat(userPutResponse.getJob(), is(equalTo(defaultUser.getJob())));
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    @Description("delete_a_user_test")
    public void delete_a_user_test() {
        var response = UserApiHelper.deleteUserById("2");
        assertThat(response.getStatusCode(), is(204));
        UserData singleUserById = UserApiHelper.getSingleUserById("2").as(SingleUser.class).getData();;
        // Since it reqres.in apis CRUD operations do not have any real affect, user will not be actually deleted
        assertThat(UserApiHelper.getAllUsers(0, 12).getData(), hasItem(singleUserById));
    }

    @AfterClass
    public void cleanTestData() {
        createdUsersId.forEach(id -> UserApiHelper.deleteUserById(id));
    }
}
