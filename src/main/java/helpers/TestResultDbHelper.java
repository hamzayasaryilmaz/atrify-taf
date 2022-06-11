package helpers;

import db.DB;
import db.models.TestResult;
import org.testng.ITestResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestResultDbHelper {
    public static List<TestResult> getTestResults() {
        List<TestResult> testResultList = new ArrayList<>();
        ResultSet resultSet = DB.executeQuery("select * from test_result");
        try {
            while (resultSet.next()) {
                TestResult testResult = new TestResult();
                testResult.setId(resultSet.getInt("id"));
                testResult.setName(resultSet.getString("name"));
                testResult.setStatus(resultSet.getString("status"));
                testResult.setUpdateDate(resultSet.getTimestamp("update_date"));
                testResult.setFailureCounter(resultSet.getInt("failure_counter"));
                testResultList.add(testResult);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        } finally {
            DB.closeConnection();
        }
        return testResultList;
    }

    public static void recordTestResult(ITestResult result) throws SQLException {
        boolean existed = false;
        int failureCounter = 0;
        String testResultStatus = TestResultDbHelper.getTestResultStatus(result.getStatus());

        String sql = "SELECT * FROM test_result WHERE name = '" + result.getName() + "'";
        ResultSet resultSet = DB.executeQuery(sql);
        while (resultSet.next()) {
            existed = true;
            failureCounter = resultSet.getInt("failure_counter");
        }

        int counter = !testResultStatus.equals("Success") ? (failureCounter + 1) : failureCounter;

        if (existed) {
            String updateSql = "UPDATE test_result\n" +
                    "SET status = '" + testResultStatus
                    + "', update_date = '" + new Timestamp(new Date().getTime())
                    + "', failure_counter = " + counter + "\n"
                    + "WHERE name = '" + result.getName() + "';";
            int i = DB.executeUpdate(updateSql);
            System.out.println(i);
        } else {
            String insertSql = "INSERT INTO test_result\n" +
                    "            (name,\n" +
                    "             status,\n" +
                    "             update_date,\n" +
                    "             failure_counter)\n" +
                    "VALUES      ('" + result.getName() + "',\n" +
                    "             '" + testResultStatus + "',\n" +
                    "             '" + new Timestamp(new Date().getTime()) + "',\n" +
                    "             " + counter + "); ";
            int i = DB.executeUpdate(insertSql);
            System.out.println(i);
        }
    }

    public static String getTestResultStatus(int status) {
        if (status == ITestResult.SUCCESS)
            return "Success";
        else if (status == ITestResult.FAILURE)
            return "Failure";
        else if (status == ITestResult.SKIP)
            return "Skip";
        else
            return "Unknown";
    }
}
