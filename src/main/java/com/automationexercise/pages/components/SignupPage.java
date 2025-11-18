package com.automationexercise.pages.components;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.media.ScreenRecordManager;
import com.automationexercise.media.ScreenShotsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupPage {
    private final GUIDriver driver;

    public SignupPage(GUIDriver driver) {
        this.driver = driver;
    }


    private final By namefield = By.cssSelector("[data-qa=\"name\"]");
    private final By emailfield = By.cssSelector("[data-qa=\"email\"]");
    private final By passwordfield = By.cssSelector("[data-qa=\"password\"]");
    private final By daysdropdown = By.cssSelector("[data-qa=\"days\"]");
    private final By monthsdropdown = By.cssSelector("[data-qa=\"months\"]");
    private final By yearsdropdown = By.cssSelector("[data-qa=\"years\"]");
    private final By newslettercheckbox = By.id("newsletter");
    private final By offerscheckbox = By.id("optin");
    private final By firstnametextfield = By.cssSelector("[data-qa=\"first_name\"]");
    private final By lastnametextfield = By.cssSelector("[data-qa=\"last_name\"]");
    private final By companytextfield = By.cssSelector("[data-qa=\"company\"]");
    private final By address1textfield = By.cssSelector("[data-qa=\"address\"]");
    private final By address2textfield = By.cssSelector("[data-qa=\"address2\"]");
    private final By countrydropdown = By.cssSelector("[data-qa=\"country\"]");
    private final By statefield = By.cssSelector("[data-qa=\"state\"]");
    private final By cityfield = By.cssSelector("[data-qa=\"city\"]");
    private final By zipcodefield = By.cssSelector("[data-qa=\"zipcode\"]");
    private final By mobiletextfield = By.cssSelector("[data-qa=\"mobile_number\"]");
    private final By createaccountbutton = By.cssSelector("[data-qa=\"create-account\"]");
    private final By accountcreatedlabel = By.tagName("b");
    private final By continuebutton = By.cssSelector("[data-qa=\"continue-button\"]");


    @Step("Choose Title: {title}")
    private SignupPage chooseTitle(String title) {
        By titlelocator = By.xpath("//input[@value='" + title + "']");
        driver.element().clickElement(titlelocator);
        return this;
    }


    @Step("Fill Signup Form and Create Account")
    public SignupPage fillSignupFormAndCreateAccount(String title,
                                                     String password,
                                                     String day,
                                                     String month,
                                                     String year,
                                                     String firstName,
                                                     String lastName,
                                                     String company,
                                                     String address1,
                                                     String address2,
                                                     String country,
                                                     String state,
                                                     String city,
                                                     String zipcode,
                                                     String mobile) {
        chooseTitle(title);
        driver.element().typeText(passwordfield, password);
        driver.element().selectFromDropdownByVisibleText(daysdropdown, day);
        driver.element().selectFromDropdownByVisibleText(monthsdropdown, month);
        driver.element().selectFromDropdownByVisibleText(yearsdropdown, year);
        driver.element().clickElement(newslettercheckbox);
        driver.element().clickElement(offerscheckbox);
        driver.element().typeText(firstnametextfield, firstName);
        driver.element().typeText(lastnametextfield, lastName);
        driver.element().typeText(companytextfield, company);
        driver.element().typeText(address1textfield, address1);
        driver.element().typeText(address2textfield, address2);
        driver.element().selectFromDropdownByVisibleText(countrydropdown, country);
        driver.element().typeText(statefield, state);
        driver.element().typeText(cityfield, city);
        driver.element().typeText(zipcodefield, zipcode);
        driver.element().typeText(mobiletextfield, mobile);
        return this;
    }

    @Step("Click on Create Account Button")
    public SignupPage clickOnCreateAccountButton() {
        driver.element().clickElement(createaccountbutton);
        return this;
    }

    @Step("Click on Continue Button")
    public NavigationBarComponent clickOnContinueButton() {
        driver.element().clickElement(continuebutton);
        return new NavigationBarComponent(driver);
    }

    @Step("Verify Account Created Label is Visible")
    public SignupPage isAccountCreatedLabelVisible() {

        driver.verify().isElementVisible(accountcreatedlabel);
        if (driver.element().getElementText(accountcreatedlabel).equals("ACCOUNT CREATED!")) {
            ScreenShotsManager.takeFullPageScreenshot(driver.get(), "AccountCreatedLabelVisible");

        }
        return this;
    }

}