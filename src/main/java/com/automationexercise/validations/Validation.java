package com.automationexercise.validations;

import com.automationexercise.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

//Soft assertion class
//All soft assertion methods are available through this class
//This class extends BaseAssertion class
//So all methods of BaseAssertion class are available through this class
//This class is used to perform soft assertions in the test cases
//Example: Validation.assertTrue(condition, "Condition is not true");
public class Validation extends BaseAssertion {
    private static SoftAssert softAssert = new SoftAssert();
    private static boolean used = false;

    public Validation() {

    }

    public Validation(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void assertTrue(boolean condition, String message) {
        used = true;
        softAssert.assertTrue(condition, message);

    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        used = true;
        softAssert.assertFalse(condition, message);

    }

    @Override
    protected void assertEquals(Object actual, Object expected, String message) {
        used = true;
        softAssert.assertEquals(actual, expected, message);

    }

    @Override
    protected void assertNotEquals(Object actual, Object expected, String message) {
        used = true;
        softAssert.assertNotEquals(actual, expected, message);

    }

    @Override
    protected void fail(String message) {
        used = true;
        softAssert.fail(message);

    }

    public static void assertAll(ITestResult result) {
        if (!used) return;
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            LogsManager.error("Soft assertion failed: " + e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        } finally {
            softAssert = new SoftAssert(); // Reset for future use
        }
    }
}
