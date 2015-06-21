package com.cityindex;

import com.cityindex.annotation.Condition;
import com.cityindex.annotation.PostCondition;
import com.cityindex.annotation.PreCondition;
import com.cityindex.exception.TestException;
import com.cityindex.manager.TestManager;
import com.cityindex.param.ConfigParam;
import com.cityindex.param.ParamsParser;
import com.cityindex.screen.Screen;
import com.cityindex.tests.LoginTests;
import com.cityindex.utils.TestHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import static com.cityindex.utils.LoggerUtil.e;
import static com.cityindex.utils.LoggerUtil.i;


public class BaseTestRunner {

    protected PreCondition mPreCondition = null;
    protected PostCondition mPostCondition = null;
    protected TestHelper testHelper;
    protected TestManager testManager;
    protected ParamsParser paramsParser;
    private static AppiumDriver driver;
//    protected Preparer preparer;

    public static void main (String... args) {
        BaseTestRunner baseTestRunner = new BaseTestRunner();
        baseTestRunner.setUp(args);
        try {
            baseTestRunner.runTest(baseTestRunner.testManager);
        } catch (TestException e) {
            e.printStackTrace();
        }
        baseTestRunner.tearDown();
    }

    protected void runTest(TestManager testManager) throws TestException {
        paramsParser.getTestId();
        Screen screen = null;
        switch ("LoginTests"){
            case "LoginTests":
            screen = new LoginTests(testManager);
                break;
            case "":
                break;
        }
        screen.runTest("testDemo");
    }

    protected void setUp(String... args) {
        i("setUp");
        paramsParser = ParamsParser.getInstance();
        if(!paramsParser.parse(args))
            return;
        testManager = TestManager.getInstance();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        File app = new File(testManager.getPathToBuild());
        if (!app.exists()){
            e("App by path: " + testManager.getPathToBuild() + " not found");
            System.exit(1);
        }
        capabilities.setCapability("platformVersion", testManager.getOsDevice());
        String deviceName = testManager.getDeviceName();
        capabilities.setCapability("deviceName", deviceName);
        if(!deviceName.toLowerCase().contains("simulator")){
            capabilities.setCapability("udid", testManager.getDeviceId());
        }
//        capabilities.setCapability("bundleid", TestManager.configManager.getProperty(ConfigurationParametersEnum.IOS_DEVICE_ID.name()));
        //        capabilities.setCapability("language", "ar");
        capabilities.setCapability("app",app.getAbsolutePath());
        try {
            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        testManager.setDriver(driver);

//        parseAnnotations();
//        try {
//            processPreConditions();
//        } catch (TestException e) {
//            e.printStackTrace();
//        }
    }

//    private void parseAnnotations() {
//        for(Method m: this.getClass().getMethods()) {
//            if (!m.getName().contains(paramsParser.getTestId())) continue;
//
//            i("ANNOTATIONS FOUND!");
//            for(Annotation a: m.getDeclaredAnnotations()) {
//                if(a instanceof PreCondition) {
//                    i("PreCondition FOUND!");
//                    mPreCondition = (PreCondition) a;
//                } else if(a instanceof PostCondition) {
//                    i("PostCondition FOUND!");
//                    mPostCondition = (PostCondition) a;
//                }
//            }
//        }
//        testManager.testCaseInfo.setTitle(mPreCondition.testTitle());
//        testManager.testCaseInfo.setId(mPreCondition.testId());
//    }

//    private void processPreConditions() throws TestException {
//        if (mPreCondition == null) return;
//
//        Condition[] preconditionActions = mPreCondition.preConditions();
//        for(Condition precondition : preconditionActions) {
//            testManager.addStep("PRECONDITION: " + precondition.name());
//            executeCondition(precondition);
//        }
//    }

//    private void processPostConditions() throws Exception {
//        if(mPostCondition == null) return;
//        Condition[] postConditions = mPostCondition.postConditions();
//        for(Condition postCondition : postConditions) {
//            testManager.addStep("POST CONDITION: " + postCondition.name());
//            executeCondition(postCondition);
//        }
//    }

//    private void executeCondition(Condition condition) throws TestException {
//        i("Annotation condition : " + condition.name());
//        testManager.addStep("Annotation condition:" + condition.name());
//        switch (condition) {
//            case LOGIN:
//                break;
//            default: break;
//        }
//    }

    protected void tearDown() {
        i("tearDown");
//        try {
//            processPostConditions();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        driver.quit();
    }
}
