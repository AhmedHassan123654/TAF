package com.automationexercise.pages.components;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.utils.dataReader.PropertyReader;
import com.automationexercise.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {
    private final GUIDriver driver;
    public NavigationBarComponent navigationBarComponent;

    public ProductsPage(GUIDriver driver) {
        this.driver = driver;
        this.navigationBarComponent = new NavigationBarComponent(driver);
    }

    //variables
    public String productpage = "/products";
    //locators
    private final By searchInputField = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By itemaddedToCartModal = By.cssSelector(".modal-body > p");
    private final By viewCartButton = By.cssSelector("p>[href=\"/view_cart\"]");
    private final By continueShoppingButton = By.cssSelector(".modal-footer >button");


    //dynamic locators
    private By productName(String productName) {
        return By.xpath("//div[@class='overlay-content'] /p[.='" + productName + "']");
    }

    private By productPrice(String productName) {
        return By.xpath("//div[@class='overlay-content'] /p[.='" + productName + "'] //preceding-sibling::h2");
    }

    private By hoverOverProduct(String productName) {
        return By.xpath("(//div[@class='productinfo text-center']/p[.='" + productName + "'])[1]");
    }

    private By addToCartButton(String productName) {
        return By.xpath("(//div[@class='productinfo text-center']/p[.='" + productName + "'])[1] //following-sibling::a ");
    }

    private By viewProduct(String productName) {
        return By.xpath("//p[.='" + productName + "']//following::div[@class='choose'][1]");
    }

    // actions
    @Step("Navigate to Products Page")
    public ProductsPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseURLweb") + productpage);
        return this;
    }

    @Step("Search for Product: {productName}")
    public ProductsPage searchForProduct(String productName) {
        driver.element().typeText(searchInputField, productName).clickElement(searchButton);
        return this;
    }

    @Step("Click on Add to Cart for Product: {productName}")
    public ProductsPage clickOnAddToCart(String productName) {
        driver.element().hoverOverElement(hoverOverProduct(productName)).clickElement(addToCartButton(productName));
        return this;
    }
//    @Step ("Wait For Cart modal Appear ")
//    public  ProductsPage waitforcartmodal(){
//
//        WebDriverWait wait = new WebDriverWait (driver.get(),Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-body > p")));
//        return this;
//
//
//
//    }

    @Step("Click on View Product for product: {productName}")
    public ProductDetailsPage clickOnViewProduct(String productName) {
        driver.element().clickElement(viewProduct(productName));
        return new ProductDetailsPage(driver);
    }

    @Step("Click on View Cart")
    public CartPage clickOnViewCart() {
        driver.element().clickElement(viewCartButton);
        return new CartPage(driver);
    }

    @Step("Click on Continue Shopping")
    public ProductsPage clickOnContinueShopping() {
        driver.element().clickElement(continueShoppingButton);
        return this;
    }

    // validations
    @Step("Validate Product Details for Product: {productName} With Price: {productPrice}")
    public ProductsPage validateProductDetails(String productName, String productPrice) {
        String ActualproductName = driver.element().hoverOverElement(productName(productName)).getElementText(productName(productName));
        String ActualproductPrice = driver.element().hoverOverElement(productName(productName)).getElementText(this.productPrice(productName));
        LogsManager.info("Validating product details for: " + ActualproductName, " with price: " + ActualproductPrice);
        driver.validation().Equals(ActualproductName, productName, "Product Name does not match");
        driver.validation().Equals(ActualproductPrice, productPrice, "Product Price does not match");
        return this;
    }

    @Step("Validate Item Added to Cart Modal is Displayed")
    public ProductsPage validateItemAddedToCartModalIsDisplayed(String expectedModalText) {
        String actualModalText = driver.element().getElementText(itemaddedToCartModal);
        LogsManager.info("Validating Item Added to Cart Modal Text. Actual: ", actualModalText);
        driver.verify().Equals(actualModalText, expectedModalText, "Item Added to Cart Modal Text does not match");
        return this;
    }

    @Step("Validate item added label contain: {expectedText}")
    public ProductsPage validateItemAddedLabel(String expectedText) {
        String actualTxt = driver.element().getElementText(itemaddedToCartModal);
        LogsManager.info("Validating item added label: " + actualTxt);
        driver.verify().Equals(actualTxt, expectedText, "Item added label does not match expected text");
        return this;
    }

}
