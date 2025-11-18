package com.automationexercise.pages.components;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.media.ScreenShotsManager;
import com.automationexercise.pages.components.NavigationBarComponent;
import com.automationexercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupLoginPage {
    public NavigationBarComponent navigationBarComponent;
    private final String loginsignupendpoint = "/login";
    private GUIDriver driver;

    public SignupLoginPage(GUIDriver driver) {
        this.navigationBarComponent = new NavigationBarComponent(driver);
        this.driver = driver;
    }


    //Locators
    private final By loginid = By.cssSelector("[data-qa=\"login-email\"]");
    private final By loginpassword = By.cssSelector("[data-qa=\"login-password\"]");
    private final By loginbutton = By.cssSelector("[data-qa=\"login-button\"]");
    private final By signupid = By.cssSelector("[data-qa=\"signup-name\"]");
    private final By signupemail = By.cssSelector("[data-qa=\"signup-email\"]");
    private final By signupbutton = By.cssSelector("[data-qa=\"signup-button\"]");
    private final By loginformlabel = By.xpath("//h2[.='Login to your account']");
    private final By errormsgloginlabel = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/p");
    private final By errormsgsignuplabel = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/p");


    //Actions
    @Step("Enter Login Email: {email} in Login Form")
    public SignupLoginPage enterLoginEmail(String email) {
        driver.element().typeText(loginid, email);
        return this;
    }

    @Step("Navigate to Signup/Login Page")
    public SignupLoginPage navigateToSignupLoginPage() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseURLweb") + loginsignupendpoint);
        return this;
    }

    @Step("Enter Login Password: {password} in Login Form")
    public SignupLoginPage enterLoginPassword(String password) {
        driver.element().typeText(loginpassword, password);
        return this;
    }

    @Step("Click on Login Button in Login Form")
    public SignupLoginPage clickLoginButton() {
        driver.element().clickElement(loginbutton);
        return this;
    }

    @Step("Enter Signup Name: {name} in Signup Form")
    public SignupLoginPage enterSignupName(String name) {
        driver.element().typeText(signupid, name);
        return this;
    }

    @Step("Enter Signup Email: {email} in Signup Form")
    public SignupLoginPage enterSignupEmail(String email) {
        driver.element().typeText(signupemail, email);
        return this;
    }

    @Step("Click on Signup Button in Signup Form")
    public SignupLoginPage clickOnSignupButton() {
        driver.element().clickElement(signupbutton);
        return new SignupLoginPage(driver);
    }

    @Step("Click on Signup Button in Signup Form to checkout")
    public SignupPage clickOnSignupButtontocheakout() {
        driver.element().clickElement(signupbutton);
        return new SignupPage(driver);
    }

    //Validations
    @Step("Verify Login Form is Visible")
    public boolean isLoginFormVisible() {
        return driver.verify().isElementVisible(loginformlabel);
    }

    @Step("Get Error Message for Invalid Login Attempt")
    public SignupLoginPage getErrorMessageForInvalidLoginAttempt(String errorMessage) {
        String actualMessage = driver.element().getElementText(errormsgloginlabel);
        driver.verify().Equals(actualMessage, errorMessage, "Your email or password is incorrect!");
        return this;
    }

    @Step("Get Error Message for Invalid Signup Attempt")
    public SignupLoginPage getErrorMessageForInvalidSignupAttempt(String errorMessage) {
        String actualMessage = driver.element().getElementText(errormsgsignuplabel);
        driver.verify().Equals(actualMessage, errorMessage, "Email Address already exist!");
        return this;
    }

    @Step("Verify Error Message When Account Created Before")
    public SignupLoginPage verifyErrorMessageWhenAccountCreatedBefore(String errorMessage) {
        String actualErrorMessage = driver.element().getElementText(errormsgsignuplabel);
        String expectedErrorMessage = "Email Address already exist!";
        driver.verify().Equals(actualErrorMessage, expectedErrorMessage, errorMessage);
        ScreenShotsManager.takeFullPageScreenshot(driver.get(), "ErrorMessageWhenAccountCreatedBefore");
        return this;
    }

}
