package com.automationexercise.pages.components;

import com.automationexercise.drivers.GUIDriver;
import org.openqa.selenium.By;

import java.util.List;

public class CheckoutPage {
    private GUIDriver driver;

    public CheckoutPage(GUIDriver driver) {
        this.driver = driver;
    }

    //variables
    //locators
    private final By checksumofproducts = By.xpath("//td/p[@class='cart_total_price']");
    private final By totalAmount = By.xpath("(//p[@class='cart_total_price'])[last()]");
    private final By placeOrderButton = By.xpath("//a[.='Place Order']");
    private final By commentTextArea = By.name("message");
    private final By subscribtionemailinputfield = By.id("susbscribe_email");
    private final By subscribebutton = By.xpath("//form[@class='searchform']//button[@type='submit']");
    private final By successfullsubscribemessage = By.xpath("//div[@id='success-subscribe']//div[contains(@class,'alert-success')]");
    //delivery address locators
    private final By deliveryname = By.xpath("//ul[@id='address_delivery'] /li[@class='address_firstname address_lastname']");
    private final By deliverycompany = By.xpath("(//ul[@id='address_delivery'] /li[@class='address_address1 address_address2'])[1]");
    private final By deliveryaddress1 = By.xpath("(//ul[@id='address_delivery'] /li[@class='address_address1 address_address2'])[2]");
    private final By deliveryaddress2 = By.xpath("(//ul[@id='address_delivery'] /li[@class='address_address1 address_address2'])[3]");
    private final By deliverycity = By.xpath("//ul[@id='address_delivery'] /li[@class='address_city address_state_name address_postcode']");
    private final By deliverycountry = By.xpath("//ul[@id='address_delivery'] /li[@class='address_country_name']");
    private final By deliveryphone = By.xpath("//ul[@id='address_delivery'] /li[@class='address_phone']");
    //billing address locators
    private final By billingname = By.xpath("//ul[@id='address_invoice'] /li[@class='address_firstname address_lastname']");
    private final By billingcompany = By.xpath("(//ul[@id='address_invoice'] /li[@class='address_address1 address_address2'])[1]");
    private final By billingaddress1 = By.xpath("(//ul[@id='address_invoice'] /li[@class='address_address1 address_address2'])[2]");
    private final By billingaddress2 = By.xpath("(//ul[@id='address_invoice'] /li[@class='address_address1 address_address2'])[3]");
    private final By billingcity = By.xpath("//ul[@id='address_invoice'] /li[@class='address_city address_state_name address_postcode']");
    private final By billingcountry = By.xpath("//ul[@id='address_invoice'] /li[@class='address_country_name']");
    private final By billingphone = By.xpath("//ul[@id='address_invoice'] /li[@class='address_phone']");


    //dynamic locators
    private By productNameInCheckout(String productName) {
        return By.xpath("(//td/h4[.='" + productName + "'])[1]");
    }

    private By productPriceInCheckout(String productName) {
        return By.xpath("(//td/h4[.='" + productName + "']/parent::td/following-sibling::td[@class='cart_price']/p)[1]");
    }

    private By productQuantityInCheckout(String productName) {
        return By.xpath("(//td/h4[.='" + productName + "']/parent::td/following-sibling::td[@class='cart_quantity']/button)[1]");
    }

    private By productTotalPriceInCheckout(String productName) {
        return By.xpath("(//td/h4[.='" + productName + "']/parent ::td/following-sibling::td[@class='cart_total']/p)[1]");
    }

    //actions
    public String getTotalAmount() {
        return driver.element().getElementText(totalAmount);
    }

    public PaymentPage clickPlaceOrderButton() {
        driver.element().clickElement(placeOrderButton);
        return new PaymentPage(driver);
    }

    public CheckoutPage enterComment(String comment) {
        driver.element().typeText(commentTextArea, comment);
        return this;
    }
    public CheckoutPage enterSubscriptionEmail(String email) {
        driver.element().typeText(subscribtionemailinputfield, email);
        return this;
    }
    public CheckoutPage clickSubscribeButton() {
        driver.element().clickElement(subscribebutton);
        return this;
    }
    //validations
    public CheckoutPage verifyProductDetailsInCheckout(String productName, String productPrice, String productQuantity, String totalPrice) {
        String actualProductName = driver.element().getElementText(productNameInCheckout(productName));
        String actualProductPrice = driver.element().getElementText(productPriceInCheckout(productName));
        String actualProductQuantity = driver.element().getElementText(productQuantityInCheckout(productName));
        String actualTotalPrice = driver.element().getElementText(productTotalPriceInCheckout(productName));
        driver.verify().Equals(actualProductName, productName, "Product name in checkout does not match");
        driver.verify().Equals(actualProductPrice, productPrice, "Product price in checkout does not match");
        driver.verify().Equals(actualProductQuantity, productQuantity, "Product quantity in checkout does not match");
        driver.verify().Equals(actualTotalPrice, totalPrice, "Product total price in checkout does not match");
        return this;
    }
    public CheckoutPage verifyDeliveryAddress(String name, String company, String address1, String address2, String city, String country, String phone) {
        driver.verify().Equals(driver.element().getElementText(deliveryname), name, "Delivery name does not match");
        driver.verify().Equals(driver.element().getElementText(deliverycompany), company, "Delivery company does not match");
        driver.verify().Equals(driver.element().getElementText(deliveryaddress1), address1, "Delivery address1 does not match");
        driver.verify().Equals(driver.element().getElementText(deliveryaddress2), address2, "Delivery address2 does not match");
        String actualCityText = driver.element().getElementText(deliverycity);
        if (!actualCityText.contains(city)) {
            throw new AssertionError("Delivery city does not contain expected value: [" + city + "] but found [" + actualCityText + "]");
        }

        driver.verify().Equals(driver.element().getElementText(deliverycountry), country, "Delivery country does not match");
        driver.verify().Equals(driver.element().getElementText(deliveryphone), phone, "Delivery phone does not match");
        return this;
    }
    public CheckoutPage verifyBillingAddress(String name, String company, String address1, String address2, String city, String country, String phone) {
        driver.verify().Equals(driver.element().getElementText(billingname), name, "Billing name does not match");
        driver.verify().Equals(driver.element().getElementText(billingcompany), company, "Billing company does not match");
        driver.verify().Equals(driver.element().getElementText(billingaddress1), address1, "Billing address1 does not match");
        driver.verify().Equals(driver.element().getElementText(billingaddress2), address2, "Billing address2 does not match");
        String actualCityText = driver.element().getElementText(billingcity);
        if (!actualCityText.contains(city)) {
            throw new AssertionError("Billing city does not contain expected value: [" + city + "] but found [" + actualCityText + "]");
        }

        driver.verify().Equals(driver.element().getElementText(billingcountry), country, "Billing country does not match");
        driver.verify().Equals(driver.element().getElementText(billingphone), phone, "Billing phone does not match");
        return this;
    }
    public CheckoutPage verifySuccessfulSubscribeMessage(String expectedMessage) {
        String actualMessage =  driver.element().getElementText(successfullsubscribemessage);
        driver.verify().Equals(actualMessage, expectedMessage, "Subscribe success message does not match");
        return this;
    }
    public CheckoutPage verifyCalculatedTotalMatchesDisplayedTotal(){

        List<String> allTotalsText = driver.element().getElementsText(checksumofproducts);
        int calculatedTotal = 0;

        for (int i = 0; i < allTotalsText.size() - 1; i++) {
            String priceText = allTotalsText.get(i).replaceAll("[^0-9]", "");
            calculatedTotal += Integer.parseInt(priceText);
        }

        String displayedTotalText = allTotalsText.get(allTotalsText.size() - 1).replaceAll("[^0-9]", "");
        int displayedTotal = Integer.parseInt(displayedTotalText);

        driver.verify().Equals(calculatedTotal, displayedTotal, "Calculated total does not match displayed total amount");
        return this;



    }
}
