package com.cityindex.screen;

import com.cityindex.utils.TestHelper;
import io.appium.java_client.AppiumDriver;


public class Screen {
    protected TestHelper testHelper;
    protected AppiumDriver driver;
    public Screen(AppiumDriver driver) {
        this.driver = driver;
        testHelper = new TestHelper(driver);
    }
}
