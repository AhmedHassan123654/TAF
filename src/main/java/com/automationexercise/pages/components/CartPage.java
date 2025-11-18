package com.automationexercise.pages.components;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    private GUIDriver driver;

    public CartPage(GUIDriver driver) {
        this.driver = driver;
    }

    //var
    private String cartendpoint = "/view_cart";


    //locators
    //td/h4[.='Men Tshirt']
    private final By registerLoginBtn = By.xpath("//a[.='Register / Login']");
    private final By proceedToCheckoutBtn = By.xpath("//a[.='Proceed To Checkout']");


    //dynamic locators
    private By productNameInCart(String productName) {
        return By.xpath("(//td/h4[.='" + productName + "'])[1]");
    }

    private By productPriceInCart(String productName) {
        return By.xpath("(//td/h4[.='" + productName + "']/parent::td/following-sibling::td[@class='cart_price']/p)[1]");
    }

    private By productQuantityInCart(String productName) {
        return By.xpath("(//td/h4[.='" + productName + "']/parent::td/following-sibling::td[@class='cart_quantity']/button)[1]");
    }

    private By productTotalPriceInCart(String productName) {
        return By.xpath("(//td/h4[.='" + productName + "']/parent::td/following-sibling::td[@class='cart_total']/p)[1]");
    }

    private By deleteProductBtnInCart(String productName) {
        return By.xpath("(//td/h4[.='" + productName + "']/parent::td/following-sibling::td/a)[1]");
    }


    //actions
    @Step("Navigate to Cart Page")
    public CartPage navigateToCartPage() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseURLweb") + cartendpoint);
        return this;
    }

    @Step("Click Proceed to Checkout With Login")
    public CheckoutPage clickProceedToCheckout() {
        driver.element().clickElement(proceedToCheckoutBtn);
        return new CheckoutPage(driver);
    }

    @Step("Click proceed to Checkout Without Login")
    public CartPage clickProceedToCheckoutWithoutLogin() {
        driver.element().clickElement(proceedToCheckoutBtn);
        return new CartPage(driver);
    }



    @Step("Delete Product: {productName} from Cart")
    public CartPage deleteProductFromCart(String productName) {
        driver.element().clickElement(deleteProductBtnInCart(productName));
        return this;
    }
    @Step("Click on Register / Login Button from Cart Page")
    public SignupLoginPage clickOnRegisterLoginButtonFromCartPage() {
        driver.element().clickElement(registerLoginBtn);
        return new SignupLoginPage(driver);
    }

    //validations
    @Step("Verify product details in Cart")
    public CartPage verifyproductdetails(String productName, String productPrice, String productQuantity, String totalPrice) {
        String actualProductName = driver.element().getElementText(productNameInCart(productName));
        String actualProductPrice = driver.element().getElementText(productPriceInCart(productName));
        String actualProductQuantity = driver.element().getElementText(productQuantityInCart(productName));
        String actualTotalPrice = driver.element().getElementText(productTotalPriceInCart(productName));
        driver.verify().Equals(actualProductName, productName, "Product name in cart does not match");
        driver.verify().Equals(actualProductPrice, productPrice, "Product price in cart does not match");
        driver.verify().Equals(actualProductQuantity, productQuantity, "Product quantity in cart does not match");
        driver.verify().Equals(actualTotalPrice, totalPrice, "Product total price in cart does not match");
        return this;
    }


}
