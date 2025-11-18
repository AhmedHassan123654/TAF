package com.automationexercise.pages.components;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.utils.dataReader.PropertyReader;
import com.automationexercise.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarComponent {
    private GUIDriver driver;

    public NavigationBarComponent(GUIDriver driver) {
        this.driver = driver;
    }

    //elements
    private final By homeButton = By.xpath("//a[.=' Home']");
    private final By productsButton = By.cssSelector("a[href=\"/products\"]");
    private final By cartButton = By.xpath("//a[.=' Cart']");
    private final By signupLoginButton = By.cssSelector("a[href=\"/login\"]");
    private final By testCasesButton = By.cssSelector("a[href=\"/test_cases\"]");
    private final By apiTestingButton = By.cssSelector("a[href=\"/api_list\"]");
    private final By videosButton = By.cssSelector("a[href=\"https://www.youtube.com/c/AutomationExercise\"]");
    private final By contactUsButton = By.cssSelector("a[href=\"/contact_us\"]");
    private final By logoutButton = By.cssSelector("a[href=\"/logout\"]");
    private final By homepagelabel = By.cssSelector("h1 > span");
    private final By deleteAccountButton = By.xpath("//a[.=' Delete Account']");
    private final By userLabel = By.tagName("b");

    //actions
    @Step("Navigate to Home Page")
    public NavigationBarComponent navigate() {

        driver.browser().navigateTo(PropertyReader.getProperty("baseURLweb"));
        return this;
    }

    @Step("Click on Home Button")
    public NavigationBarComponent clickOnHomeButton() {
        driver.element().clickElement(homeButton);
        return this;
    }

    @Step("Click on Products Button")
    public ProductsPage clickOnProductsButton() {
        driver.element().clickElement(productsButton);
        return new ProductsPage(driver);
    }

    @Step("Click on Cart Button")
    public CartPage clickOnCartButton() {
        driver.element().clickElement(cartButton);
        return new CartPage(driver);
    }

    @Step("Click on Logout Button")
    public LogoutPage clickOnLogoutButton() {
        driver.element().clickElement(logoutButton);
        return new LogoutPage(driver);
    }

    @Step("Click on Signup/Login Button")
    public SignupLoginPage clickOnSignupLoginButton() {
        driver.element().clickElement(signupLoginButton);
        return new SignupLoginPage(driver);
    }

    @Step("Click on Test Cases Button")
    public TestCasesPage clickOnTestCasesButton() {
        driver.element().clickElement(testCasesButton);
        return new TestCasesPage(driver);
    }

    @Step("Click on Delete Account Button")
    public DeleteAccountPage clickOnDeleteAccountButton() {
        driver.element().clickElement(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }

    @Step("Click on ContactUs Button Button")
    public ContactUsPage clickOnContactUsButton() {
        driver.element().clickElement(contactUsButton);
        return new ContactUsPage(driver);
    }

    //validations
    @Step("Verify Home Page Label")
    public NavigationBarComponent verifyHomePage() {
        driver.verify().isElementVisible(homepagelabel);
        return this;
    }

    @Step("Verify User Label")
    public NavigationBarComponent verifyUserLabel(String expectedName) {
        String actualName = driver.element().getElementText(userLabel);
        LogsManager.info("Verifying user label: " + actualName);
        driver.verify().Equals(actualName, expectedName, "User label does not match. Expected: " + expectedName + ", Actual: " + actualName);
        return this;

    }


}
