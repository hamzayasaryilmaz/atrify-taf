package com.pagonxt.taf;

import base.TestBase;
import db.models.TestResult;
import helpers.TestResultDbHelper;
import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DbTests extends TestBase {
    @Test
    @Description("database_connection_test")
    public void get_test_result_test(Method method) {
        List<TestResult> testResults = TestResultDbHelper.getTestResults();
        assertThat(testResults, hasItem(allOf(Matchers.<TestResult>hasProperty("name", is(method.getName())))));
    }
}
