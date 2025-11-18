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

@Epic(" Automation Exercise Products Tests")
@Feature(" UI Products Management - Products Tests")
@Story(" Products Management Tests")
@Owner("QA Team-Ahmed Hassan")
public class ProductsTest extends BaseTest {
    protected JsonReader testdata;


    @Test
    @Description("Verify Search Product Functionality Test Case")
    public void verifySearchProductFunctionalityTCWithoutLogin() {
        new ProductsPage(driver).navigate()
                .searchForProduct(testdata.getJsonreader("SearchProduct.name"))
                .validateProductDetails(testdata.getJsonreader("SearchProduct.name"), testdata.getJsonreader("SearchProduct.price"));

    }


    @Test
    @Description("Add Products to Cart Without Login Test Case")
    public void addProductsToCartWithoutLoginTC() {
        new ProductsPage(driver).navigate()
                .clickOnAddToCart(testdata.getJsonreader("product1.name"))
                .validateItemAddedToCartModalIsDisplayed(testdata.getJsonreader("messages.cartAdded"));


    }


    @BeforeClass
    public void precondition() {
        // Initialize test data
        testdata = new JsonReader("products-data");
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
                // تجاهل أي أخطاء أثناء الإغلاق
                e.printStackTrace();
            }
            driver = null;
        }
    }
}


