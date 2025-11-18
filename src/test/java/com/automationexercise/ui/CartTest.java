package com.automationexercise.ui;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.pages.components.NavigationBarComponent;
import com.automationexercise.pages.components.ProductsPage;
import com.automationexercise.tests.BaseTest;
import com.automationexercise.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("UI Cart Details")
@Story("Cart Details")
@Severity(SeverityLevel.CRITICAL)
@Owner("Ahmed Hassan")

public class CartTest extends BaseTest {


    @Test
    public void verifyProductDetailsOnCartWithoutLogin() {
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testdata.getJsonreader("product.name"))
                .validateItemAddedLabel(testdata.getJsonreader("messages.cartAdded"))
                .clickOnViewCart()
                .verifyproductdetails(
                        testdata.getJsonreader("product.name"),
                        testdata.getJsonreader("product.price"),
                        testdata.getJsonreader("product.quantity"),
                        testdata.getJsonreader("product.total")
                );
    }


    //configuration
    @BeforeClass
    public void precondition() {
        // Initialize test data
        testdata = new JsonReader("cart-data");
    }

    @BeforeMethod
    public void setup() {
        driver = new GUIDriver();
        // Navigate to the home page before each test
        new NavigationBarComponent(driver).navigate();

    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            try {
                driver.quitDriver();
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver = null;
        }
    }
}
