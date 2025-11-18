package com.automationexercise.pages.components;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.utils.dataReader.PropertyReader;
import com.automationexercise.utils.logs.LogsManager;

import io.qameta.allure.Step;

import org.openqa.selenium.By;

public class ProductDetailsPage {
    private final GUIDriver driver;

    public ProductDetailsPage(GUIDriver driver) {
        this.driver = driver;
    }


    //variables
    private String productDetailsPage = "/product_details/2";

    //locators

    private final By productName = By.cssSelector(".product-information > h2");
    private final By productPrice = By.cssSelector(".product-information > span >span");
    private final By addname = By.id("name");
    private final By addemail = By.id("email");
    private final By addReview = By.cssSelector(".col-sm-12 > form >textarea");
    private final By submitReview = By.id("button-review");
    private final By reviewSuccessmsg = By.cssSelector("#review-section span");


    //actions
    @Step("Navigate to Product Details Page")
    public ProductDetailsPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseURLweb") + productDetailsPage);
        return this;
    }


    @Step("Add Review for Product: {productName}")
    public ProductDetailsPage addReview(String name, String email, String review) {
        driver.element().typeText(addname, name);
        LogsManager.info("Typed name: ", name, " in element: ", addname.toString());
        driver.element().typeText(addemail, email);
        LogsManager.info("Typed email: ", email, " in element: ", addemail.toString());
        driver.element().typeText(addReview, review);
        LogsManager.info("Typed review: ", review, " in element: ", addReview.toString());
        driver.element().clickElement(submitReview);
        LogsManager.info("Clicked on submit review button");
        return this;
    }


    //validations
    @Step("Validate Product Name and Price")
    public ProductDetailsPage validateProductDetail(String expectedName, String productprice) {
        String actualName = driver.element().getElementText(productName);
        String actualPrice = driver.element().getElementText(productPrice);
        LogsManager.info("Validating product name: ", actualName, " with expected name: ", expectedName);
        LogsManager.info("Validating product price: ", actualPrice, " with expected price: ", productprice);
        driver.validation().Equals(actualName, expectedName, "Product Name does not match");
        driver.validation().Equals(actualPrice, productprice, "Product Price does not match");
        return this;
    }

    @Step("Validate Review Success Msg")
    public ProductDetailsPage validateReviewSuccessMsg(String expectedMsg) {
        String actualMsg = driver.element().getElementText(reviewSuccessmsg);
        LogsManager.info("Validating review success msg: ", actualMsg, " with expected msg: ", expectedMsg);
        driver.validation().Equals(actualMsg, expectedMsg, "Review Success Msg does not match");
        return this;
    }


    //preconditions


    //postconditions

}
 
