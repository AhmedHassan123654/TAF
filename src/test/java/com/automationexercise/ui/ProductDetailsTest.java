package com.automationexercise.ui;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.pages.components.NavigationBarComponent;
import com.automationexercise.pages.components.ProductsPage;
import com.automationexercise.tests.BaseTest;
import com.automationexercise.utils.dataReader.JsonReader;

public class ProductDetailsTest extends BaseTest {
    protected JsonReader testdata;


    @Test
    public void verifyProductDetailsTC() {
        new ProductsPage(driver).navigate()
                .clickOnViewProduct(testdata.getJsonreader("Product.name"))
                .validateProductDetail(testdata.getJsonreader("Product.name"), testdata.getJsonreader("Product.price"));

    }

    @Test
    public void verifyReviewSuccessMsgTC() {
        new ProductsPage(driver).navigate()
                .clickOnViewProduct(testdata.getJsonreader("Product.name"))
                .addReview(testdata.getJsonreader("Review.name"), testdata.getJsonreader("Review.email"), testdata.getJsonreader("Review.review"))
                .validateReviewSuccessMsg(testdata.getJsonreader("messages.reviewSuccess"));
    }


    @BeforeClass
    public void precondition() {
        // Initialize test data
        testdata = new JsonReader("product-details-data");
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
