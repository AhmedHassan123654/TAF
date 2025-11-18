package com.automationexercise.ui;

import com.automationexercise.apis.UserManagmentAPI;
import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.pages.components.NavigationBarComponent;
import com.automationexercise.pages.components.SignupLoginPage;
import com.automationexercise.pages.components.SignupPage;
import com.automationexercise.tests.BaseTest;
import com.automationexercise.utils.TimeManager;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise Register Tests")
@Feature("UI User Management - Register Tests")
@Story("Register Tests with Valid and Invalid Credentials")
@Severity(SeverityLevel.CRITICAL)
@Owner("QA Team-Ahmed Hassan")
public class RegisterTest extends BaseTest {
    protected String timestamp = TimeManager.gettimestamp();

    //tests
    @Test
    @Description("Valid Signup Test Case")
    public void validsignupTC() {
        new SignupLoginPage(driver).navigateToSignupLoginPage()
                .enterSignupName(testdata.getJsonreader("name"))
                .enterSignupEmail(testdata.getJsonreader("email") + timestamp + "@gmail.com")
                .clickOnSignupButton();
        new SignupPage(driver)
                .fillSignupFormAndCreateAccount(
                        testdata.getJsonreader("titlemale"),
                        testdata.getJsonreader("password"),
                        testdata.getJsonreader("day"),
                        testdata.getJsonreader("month"),
                        testdata.getJsonreader("year"),
                        testdata.getJsonreader("firstName"),
                        testdata.getJsonreader("lastName"),
                        testdata.getJsonreader("company"),
                        testdata.getJsonreader("addressLine1"),
                        testdata.getJsonreader("addressLine2"),
                        testdata.getJsonreader("country"),
                        testdata.getJsonreader("zipCode"),
                        testdata.getJsonreader("state"),
                        testdata.getJsonreader("city"),
                        testdata.getJsonreader("mobileNumber")
                )
                .clickOnCreateAccountButton()
                .isAccountCreatedLabelVisible()
                .clickOnContinueButton();
        new UserManagmentAPI(driver).deleteUser(
                        testdata.getJsonreader("email") + timestamp + "@gmail.com",
                        testdata.getJsonreader("password")
                )
                .validateUserDeleted();
    }

    @Test
    @Description("Verify Error Message When Account Created Before Test Case")
    public void verifyErrorMessageWhenAccountCreatedBefore() {

        new UserManagmentAPI(driver).createUser(
                        testdata.getJsonreader("name"),
                        testdata.getJsonreader("email") + timestamp + "@gmail.com",
                        testdata.getJsonreader("password"),
                        testdata.getJsonreader("titlemale"),
                        testdata.getJsonreader("day"),
                        testdata.getJsonreader("month"),
                        testdata.getJsonreader("year"),
                        testdata.getJsonreader("firstName"),
                        testdata.getJsonreader("lastName"),
                        testdata.getJsonreader("company"),
                        testdata.getJsonreader("addressLine1"),
                        testdata.getJsonreader("addressLine2"),
                        testdata.getJsonreader("country"),
                        testdata.getJsonreader("zipCode"),
                        testdata.getJsonreader("state"),
                        testdata.getJsonreader("city"),
                        testdata.getJsonreader("mobileNumber")
                )
                .validateUser();
        new SignupLoginPage(driver).navigateToSignupLoginPage()
                .enterSignupName(testdata.getJsonreader("name"))
                .enterSignupEmail(testdata.getJsonreader("email") + timestamp + "@gmail.com")
                .clickOnSignupButton()
                .verifyErrorMessageWhenAccountCreatedBefore(testdata.getJsonreader("messages.error"));


    }

    @BeforeMethod
    public void setup() {
        if (driver != null) {
            try {
                driver.quitDriver();
            } catch (Exception ignored) {
            }
            driver = null;
        }
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
        driver.browser().closeextensionTabs();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            try {
                driver.quitDriver();
            } catch (Exception ignored) {
            }
            driver = null;
        }
    }

}
