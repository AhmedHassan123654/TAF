package com.automationexercise.tests;

import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.drivers.WebDriverProvider;
import com.automationexercise.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected JsonReader testdata;

    @BeforeClass
    public void precondition() {
        // driver= new GUIDriver();
        testdata = new JsonReader("register-data");
    }

    @Override
    public WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("❌ GUIDriver is not initialized. Check setup() method.");
        }
        return driver.get();
    }
}

//asdgsdfhasdfh

