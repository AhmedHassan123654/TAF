package com.automationexercise.ui;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.pages.components.NavigationBarComponent;
import com.automationexercise.pages.components.ProductsPage;
import com.automationexercise.tests.BaseTest;
import com.automationexercise.utils.TimeManager;
import com.automationexercise.utils.dataReader.JsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckOutTest  {
    protected GUIDriver driver;
    protected JsonReader testdata;
    protected String timestamp; // ✅ تعريف فقط بدون تهيئة


    @Test
    public void verifyProductCheckoutwithoutlogin() {
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testdata.getJsonreader("product1.name"))
                .validateItemAddedLabel(testdata.getJsonreader("messages.cartadded"))
                .clickOnViewCart()
                .clickProceedToCheckoutWithoutLogin()
                .clickOnRegisterLoginButtonFromCartPage()
                .enterSignupName(testdata.getJsonreader("name"))
                .enterSignupEmail(testdata.getJsonreader("email")+ timestamp + "@gmail.com")
                .clickOnSignupButtontocheakout()
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
                .clickOnContinueButton()
                .clickOnCartButton()
                .clickProceedToCheckout()
                .verifyBillingAddress(
                        testdata.getJsonreader("billingDetails.name"),
                        testdata.getJsonreader("billingDetails.companyName"),
                        testdata.getJsonreader("billingDetails.address1"),
                        testdata.getJsonreader("billingDetails.address2"),
                        testdata.getJsonreader("billingDetails.city"),
                        testdata.getJsonreader("billingDetails.country"),
                        testdata.getJsonreader("billingDetails.phone")

                )
                .verifyDeliveryAddress(
                        testdata.getJsonreader("deliveryDetails.name"),
                        testdata.getJsonreader("deliveryDetails.companyName"),
                        testdata.getJsonreader("deliveryDetails.address1"),
                        testdata.getJsonreader("deliveryDetails.address2"),
                        testdata.getJsonreader("deliveryDetails.city"),
                        testdata.getJsonreader("deliveryDetails.country"),
                        testdata.getJsonreader("deliveryDetails.phone")
                )
                .verifyProductDetailsInCheckout(
                        testdata.getJsonreader("product1.name"),
                        testdata.getJsonreader("product1.price"),
                        testdata.getJsonreader("product1.quantity"),
                        testdata.getJsonreader("product1.total")
                );
    }


    @Test
    public void verifyaddingmultibleProductandCheckoutwithoutlogin() {
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testdata.getJsonreader("product1.name"))
                .validateItemAddedLabel(testdata.getJsonreader("messages.cartadded"))
                .clickOnContinueShopping()
                .clickOnAddToCart(testdata.getJsonreader("product2.name"))
                .validateItemAddedLabel(testdata.getJsonreader("messages.cartadded"))
                .clickOnContinueShopping()
                .clickOnViewCart()
                .clickProceedToCheckoutWithoutLogin()
                .clickOnRegisterLoginButtonFromCartPage()
                .enterSignupName(testdata.getJsonreader("name"))
                .enterSignupEmail(testdata.getJsonreader("email")+ timestamp + "@gmail.com")
                .clickOnSignupButtontocheakout()
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
                .clickOnContinueButton()
                .clickOnCartButton()
                .clickProceedToCheckout()
                .verifyBillingAddress(
                        testdata.getJsonreader("billingDetails.name"),
                        testdata.getJsonreader("billingDetails.companyName"),
                        testdata.getJsonreader("billingDetails.address1"),
                        testdata.getJsonreader("billingDetails.address2"),
                        testdata.getJsonreader("billingDetails.city"),
                        testdata.getJsonreader("billingDetails.country"),
                        testdata.getJsonreader("billingDetails.phone")

                )
                .verifyDeliveryAddress(
                        testdata.getJsonreader("deliveryDetails.name"),
                        testdata.getJsonreader("deliveryDetails.companyName"),
                        testdata.getJsonreader("deliveryDetails.address1"),
                        testdata.getJsonreader("deliveryDetails.address2"),
                        testdata.getJsonreader("deliveryDetails.city"),
                        testdata.getJsonreader("deliveryDetails.country"),
                        testdata.getJsonreader("deliveryDetails.phone")
                )
                .verifyProductDetailsInCheckout(
                        testdata.getJsonreader("product1.name"),
                        testdata.getJsonreader("product1.price"),
                        testdata.getJsonreader("product1.quantity"),
                        testdata.getJsonreader("product1.total")
                ).verifyProductDetailsInCheckout(
                        testdata.getJsonreader("product2.name"),
                        testdata.getJsonreader("product2.price"),
                        testdata.getJsonreader("product2.quantity"),
                        testdata.getJsonreader("product2.total"))
                .verifyCalculatedTotalMatchesDisplayedTotal();
    }












    //configuration
    @BeforeClass
    public void precondition() {
        // Initialize test data
        testdata = new JsonReader("checkout-data");
    }

    @BeforeMethod
    public void setup() {
        timestamp = String.valueOf(System.currentTimeMillis());
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
