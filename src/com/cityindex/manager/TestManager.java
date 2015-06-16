package com.cityindex.manager;

import com.cityindex.exception.TestException;
import com.cityindex.utils.*;
import io.appium.java_client.AppiumDriver;
import java.util.Date;

public class TestManager {
    private static volatile TestManager instance;

    public static void setDriver(AppiumDriver driver) {
        TestManager.driver = driver;
    }

    private static FileWorker fileWorker;
    private static FileWorker fileLogWorker;
    private static String deviceId = null;
    private static String buildName = null;
    private static String network = null;
    private static String os_system = null;
    private static String hwDevice = null;
    private static String osDevice = null;
    private static String dumpKpiInfo = null;
    private static String account = null;
    private static String password = null;
    private static int timeout = 0;
    private static String[] args = null;
    private static long mLastDumpTime = 0;

    private static long mStartTime = 0;
    private static long mEndTime = 0;
    public static ConfigManager configManager = null;
    private static AppiumDriver driver;

    private TestManager(String[] args) {
        this.args = args;
        configManager = new ConfigManager();
        init();
        fileWorker = new FileWorker();
        fileLogWorker = new FileWorker(MainConstants.FILE_NAME_LOG_TESTS);
    }



    public void init(){
        if (args.length > 0)
            MainConstants.TEST_NAME = args[0].toString();
        if (args.length > 1) {
            dumpKpiInfo = args[1].toString();
        }
        if (args.length > 2){
            deviceId = args[2].toString();
        }else {
            deviceId = configManager.getProperty(ConfigurationParametersEnum.IOS_DEVICE_ID.name());
        }
        if (args.length > 3){
            buildName = args[3].toString();
        }else {
            buildName = configManager.getProperty(ConfigurationParametersEnum.BUILD_NAME.name());
        }
        if(args.length > 4){
            hwDevice = args[4].toString();
        }else{
            hwDevice = configManager.getProperty(ConfigurationParametersEnum.IOS_DEVICE.name());
        }
        if (args.length > 5){
            account = args[5].toString();
        }else {
            account = configManager.getProperty(ConfigurationParametersEnum.LOGIN.name());
        }
        if (args.length > 6){
            password = args[6].toString();
        }else{
            password = configManager.getProperty(ConfigurationParametersEnum.PASSWORD.name());
        }
        if (args.length > 7){
            parseTimeout(args[7].toString());
        }else{
            parseTimeout(TestManager.configManager.getProperty(ConfigurationParametersEnum.TIMEOUT.name()));
        }

        network = "";
        os_system = TestManager.configManager.getProperty(ConfigurationParametersEnum.IOS_PLATFORM_NAME.name());
        osDevice = TestManager.configManager.getProperty(ConfigurationParametersEnum.IOS_PLATFORM_VERSION.name());

    }

    private void parseTimeout(String line){
        try{
            timeout = Integer.parseInt(line);
        }catch (Throwable ex){
            LoggerUtils.e("Used default timeout = " + MainConstants.DEFAULT_TIMEOUT + ex.getMessage());
            timeout = MainConstants.DEFAULT_TIMEOUT;
        }
    }

    public static TestManager getInstance(String[] args){
        if(instance == null)
            synchronized (TestManager.class){
                if(instance == null)
                    instance = new TestManager(args);
            }
        return instance;
    }

    public static TestManager getInstance(){
        return getInstance(args);
    }

    public static ItemLog addLogParams(Date date, String testAction, String testData, boolean testResult){
        ItemLog itemLog = new ItemLog();
        itemLog.setBuild(getBuildName());
        itemLog.setDeviceId(getDeviceId());
        itemLog.setNet(getNetwork());
        itemLog.setHw(getHwDevice());
        itemLog.setOs(getOsDevice());
        itemLog.setSlaveId("");
        itemLog.setDate(date, "");
        itemLog.setTime(date, "");
        itemLog.setStartTime(mStartTime);
        itemLog.setEndTime(mEndTime, mLastDumpTime);
        itemLog.setTestId("AlchemyKpi");
        itemLog.setTestAction(testAction);
        itemLog.setTestData(testData);
        itemLog.setTestResult(testResult);
        return itemLog;
    }


    public static String getDeviceId() {
        return deviceId;
    }

    public static String getBuildName() {
        return buildName;
    }

    public static String getNetwork() {
        return network;
    }

    public static String getOs_system() {
        return os_system;
    }

    public static String getHwDevice() {
        return hwDevice;
    }

    public static String getOsDevice() {
        return osDevice;
    }

    public static String getDumpKpiInfo() {
        return dumpKpiInfo;
    }

    public static String getAccount() {
        return account;
    }

    public static String getPassword() {
        return password;
    }

    public static int getTimeout() {
        return timeout;
    }

    public static void failTest(String errorMsg) throws TestException{
        throw new TestException(errorMsg, driver).failTest();
    }

    public static void retestTest(String errorMsg)throws TestException{
        throw new TestException(errorMsg, driver).retest();
    }

}
