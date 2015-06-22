package com.cityindex.manager;

import com.cityindex.exception.TestException;
import com.cityindex.json.Status;
import com.cityindex.json.TestCaseInfo;
import com.cityindex.param.*;
import com.cityindex.utils.*;
import io.appium.java_client.AppiumDriver;
import static com.cityindex.utils.LoggerUtil.i;

public class TestManager {
//    private static volatile TestManager instance;

    private static TestManager testManager;
    public TestCaseInfo testCaseInfo;
    private String deviceId = null;
    private String testClass = null;
    private String deviceName = null;
    private String osDevice = null;
    private String login = null;
    private String password = null;
    public ConfigManager configManager = null;
    public static AppiumDriver driver;
    private ParamsParser paramsParser;
    private String pathToBuild;

    private TestManager() {
//        this.args = args;
        configManager = new ConfigManager();
        testCaseInfo = new TestCaseInfo();
        initProperties();
    }

    private void initProperties(){
        paramsParser = ParamsParser.getInstance();
        pathToBuild = paramsParser.getPathToBuild() == null ?
                configManager.getProperty(ConfigParam.BUILD_PATH.name()) : paramsParser.getPathToBuild();
        login = configManager.getProperty("LOGIN");
        password = configManager.getProperty("PASSWORD");
        deviceId = paramsParser.getDeviceUuid() == null ?
                configManager.getProperty(ConfigParam.IOS_DEVICE_ID.name()) : paramsParser.getDeviceUuid();
        testClass = paramsParser.getTestClass();
        osDevice = paramsParser.getDeviceOS() == null ?
                configManager.getProperty(ConfigParam.IOS_PLATFORM_VERSION.name()) : paramsParser.getDeviceOS();
        deviceName = paramsParser.getDeviceName();
    }

    public void setDriver(AppiumDriver driver) {
        this.driver = driver;
    }

    public AppiumDriver getDriver(){
        return driver;
    }

    public String getTestProperty(String key) {
        return configManager.getProperty(key);
    }

    public static TestManager getInstance() {
        if(testManager == null)
            testManager = new TestManager();
        return testManager;
    }

    public void writeResult() {
        testCaseInfo.writeResult(ParamsParser.getInstance().getPathToResultsFolder() + "/result.json");
    }

    public ParamsParser getParamsParser() {
        return paramsParser;
    }

    public ConfigManager getConfigManagerManager() {
        return configManager;
    }

    public String getPathToBuild() {
        return pathToBuild;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setTestCaseInfo(TestCaseInfo testCaseInfo) {
        this.testCaseInfo = testCaseInfo;
    }

    public TestCaseInfo getTestCaseInfo() {
        return testCaseInfo;
    }

    public String getOsDevice(){return osDevice;}
    public String getDeviceId(){return deviceId;}

    public void retest(String message) throws TestException {
        testCaseInfo.setMessage(message);
        testCaseInfo.setStatusId(4);
        throw new TestException(message, driver).retest();
    }

    public void failTest(String message) throws TestException {
        testCaseInfo.setMessage(message);
        testCaseInfo.setStatusId(Status.FAILED);
        throw new TestException(message, driver).failTest();
    }

    public String getLogin() {
        return login;
    }

    public void addStep(String step) {
        i("########################################################");
        i("STEP: " + step);
//        ScreenShotTaker.getInstance().takeScreenShot("before_" + step.replace("\"? ?\\.?,?:?", "_").replaceAll("\"?'?", ""));
        testCaseInfo.addStep(step);
        i("########################################################");
    }

}
