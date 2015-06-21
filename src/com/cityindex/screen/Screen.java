package com.cityindex.screen;

import com.cityindex.exception.TestException;
import com.cityindex.manager.TestManager;
import com.cityindex.utils.TestHelper;
import io.appium.java_client.AppiumDriver;


public class Screen{
    protected TestManager testManager;
    protected TestHelper testHelper;
    protected AppiumDriver driver;

    public Screen(TestManager testManager) {
        this.testManager = testManager;
        this.driver = this.testManager.getDriver();
        testHelper = new TestHelper(this.testManager);
    }

    public void runTest(String test) throws TestException{
    }
    //For implementation common methods to test app
}
