package api.user;

import api.user.models.UserData;
import api.user.models.UserList;
import io.qameta.allure.Step;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserApiValidations {
    @Step("Verify if calculated number of all users equal to total users")
    public static void checkCalculationOfUserList(UserList userList) {
        int calculatedTotalUserNumber = 0;
        // Total number of users
        var totalUserCount = userList.getTotal();
        // Total pages
        var totalPagesCount = userList.getTotalPages();
        // Total users per page
        var usersPerPage = userList.getPerPage();

        // Looping through total pages
        for (int i = 1; i < totalPagesCount + 1; i++) {
            // Get total number of users for current page
            List<UserData> userDataList = UserApiHelper.getAllUsers(i, 12).getData();
            // Store the number of users in current page
            calculatedTotalUserNumber += userDataList.size();
            // Verify the size of actual users with users per page
            assertThat(userDataList.size(), equalTo(usersPerPage));
        }
        // Verify total number of users with calculated number of users
        assertThat(calculatedTotalUserNumber, equalTo(totalUserCount));
    }
}
