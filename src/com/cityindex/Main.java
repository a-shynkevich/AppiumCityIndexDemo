package com.cityindex;

import com.cityindex.manager.TestManager;
import com.cityindex.screen.Login;
import com.cityindex.utils.ConfigurationParametersEnum;
import com.cityindex.utils.MainConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

import static com.cityindex.utils.LoggerUtils.i;

public class Main {

    private static AppiumDriver driver;
    private static TestManager testManager;
    private static void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        File appDir = new File(TestManager.configManager.getProperty(ConfigurationParametersEnum.BUILD_DIR.name()));
        File app = new File(appDir, TestManager.getBuildName());
        capabilities.setCapability("platformVersion", TestManager.getOsDevice());
        String deviceName = TestManager.getHwDevice();
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("language", "ar");

        //        capabilities.setCapability("platformName", TestManager.getOs_system());
//        if(!deviceName.toLowerCase().contains("simulator")){
//            capabilities.setCapability("udid", TestManager.getDeviceId());
//        }
//        capabilities.setCapability("bundleid", TestManager.configManager.getProperty(ConfigurationParametersEnum.IOS_DEVICE_ID.name()));
        capabilities.setCapability("app",app.getAbsolutePath());
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        MainConstants.FILE_NAME_LOG_TESTS = "iOs.csv";
        TestManager.setDriver(driver);

    }

    public static void tearDown() throws Exception {
        if (null != driver) {
            driver.quit();
            i("Quit driver");
        }
    }

    public static void main(String[] args) throws Exception {
            testManager = TestManager.getInstance(args);
        setUp();
        new Login(driver).signIn();
        tearDown();
    }
}
