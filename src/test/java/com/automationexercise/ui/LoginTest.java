package com.automationexercise.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automationexercise.apis.UserManagmentAPI;
import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.pages.components.NavigationBarComponent;
import com.automationexercise.pages.components.SignupLoginPage;
import com.automationexercise.tests.BaseTest;
import com.automationexercise.utils.TimeManager;
import com.automationexercise.utils.dataReader.JsonReader;

@Epic("Automation Exercise Login Tests")
@Feature("UI User Management - Login Tests")
@Story("Login Tests with Valid and Invalid Credentials")
@Owner("QA Team-Ahmed Hassan")
public class LoginTest extends BaseTest {
    protected String timestamp = TimeManager.gettimestamp();
    protected JsonReader testdata;

    @Test
    public void validLoginTC() {
        new UserManagmentAPI(driver).createUser(
                        testdata.getJsonreader("name"),
                        testdata.getJsonreader("email") + timestamp + "@gmail.com",
                        testdata.getJsonreader("password"),
                        testdata.getJsonreader("firstName"),
                        testdata.getJsonreader("lastName")

                )
                .validateUser();
        new SignupLoginPage(driver).navigateToSignupLoginPage().
                enterLoginEmail(testdata.getJsonreader("email") + timestamp + "@gmail.com").
                enterLoginPassword(testdata.getJsonreader("password")).
                clickLoginButton();
        new NavigationBarComponent(driver).verifyUserLabel(testdata.getJsonreader("name"));
        new UserManagmentAPI(driver).deleteUser(
                        testdata.getJsonreader("email") + timestamp + "@gmail.com",
                        testdata.getJsonreader("password")
                )
                .validateUserDeleted();
    }

    @Test
    public void invalidLoginusinginvalidEmailTC() {
        new UserManagmentAPI(driver).createUser(
                        testdata.getJsonreader("name"),
                        testdata.getJsonreader("email") + timestamp + "@gmail.com",
                        testdata.getJsonreader("password"),
                        testdata.getJsonreader("firstName"),
                        testdata.getJsonreader("lastName")

                )
                .validateUser();
        new SignupLoginPage(driver).navigateToSignupLoginPage().
                enterLoginEmail(testdata.getJsonreader("email") + "@gmail.com").
                enterLoginPassword(testdata.getJsonreader("password")).clickLoginButton().getErrorMessageForInvalidLoginAttempt(testdata.getJsonreader("messages.error"));

        new UserManagmentAPI(driver).deleteUser(
                        testdata.getJsonreader("email") + timestamp + "@gmail.com",
                        testdata.getJsonreader("password")
                )
                .validateUserDeleted();


    }


    @Test
    public void invalidLoginusinginvalidPasswordTC() {
        new UserManagmentAPI(driver).createUser(
                        testdata.getJsonreader("name"),
                        testdata.getJsonreader("email") + timestamp + "@gmail.com",
                        testdata.getJsonreader("password"),
                        testdata.getJsonreader("firstName"),
                        testdata.getJsonreader("lastName")

                )
                .validateUser();
        new SignupLoginPage(driver).navigateToSignupLoginPage().
                enterLoginEmail(testdata.getJsonreader("email") + timestamp + "@gmail.com").
                enterLoginPassword(testdata.getJsonreader("password") + timestamp).
                clickLoginButton()
                .getErrorMessageForInvalidLoginAttempt(testdata.getJsonreader("messages.error"));
//        new NavigationBarComponent(driver).verifyUserLabel(testdata.getJsonreader("name"));
        new UserManagmentAPI(driver).deleteUser(
                        testdata.getJsonreader("email") + timestamp + "@gmail.com",
                        testdata.getJsonreader("password")
                )
                .validateUserDeleted();
    }


    //configurations
    @BeforeClass
    public void precondition() {
        driver = new GUIDriver();
        testdata = new JsonReader("login-data");
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
