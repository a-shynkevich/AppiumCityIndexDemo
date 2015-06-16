package com.cityindex.utils;

import com.cityindex.manager.TestManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemLog {
    private String date="";
    private String time="";
    private String build="";
    private String hw="";
    private String os="";
    private String net="";
    private String deviceId="";
    private String slaveId="";
    private String testType="";
    private String testId="";
    private String testName="";
    private String testAction="";
    private String testData="";
    private String usedMemory="";
    private long startTime=0;
    private long endTime=0;
    private long totalTime=0;
    private boolean testResult=false;
    private String testCycle="";

    String dateFieldName = null;
    String timeFieldName = null;
    String buildFieldName = null;
    String hwFieldName = null;
    String osFieldName = null;
    String netFieldName = null;
    String deviceIdFieldName = null;
    String slaveIdFieldName = null;
    String testTypeFieldName = null;
    String testIdFieldName = null;
    String testNameFieldName = null;
    String testActionFieldName = null;
    String testDataFieldName = null;
    String usedMemoryFieldName = null;
    String startTimeFieldName = null;
    String endTimeFieldName = null;
    String totalTimeFieldName = null;
    String testResultFieldName = null;
    String testCycleFieldName = null;
    PropertiesManager propertiesManager;

    public ItemLog() {
        propertiesManager = TestManager.configManager.getPropertiesManager();

    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getBuild() {
        return build;
    }

    public String getHw() {
        return hw;
    }

    public String getOs() {
        return os;
    }

    public String getNet() {
        return net;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getSlaveId() {
        return slaveId;
    }

    public String getTestId() {
        return testId;
    }

    public String getTestName() {
        if(testName == null || testName.isEmpty())
            return MainConstants.TEST_NAME;
        return testName;
    }

    public String getTestAction() {
        return testAction;
    }

    public String getTestData() {
        return testData;
    }

    public String getUsedMemory() {
        return usedMemory;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public boolean getTestResult() {
        return testResult;
    }

    public String getTestCycle() {
        return testCycle;
    }

    public void setDate(Date d, String format) {
        SimpleDateFormat simpleDateFormat;
        if(format != null && !format.isEmpty()){
            simpleDateFormat = new SimpleDateFormat(format);
            this.date = simpleDateFormat.format(d);
        }else{
            simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            this.date = simpleDateFormat.format(d);
        }
    }

    public void setTime(Date t, String format) {
        SimpleDateFormat simpleTimeFormat;
        if(format != null && !format.isEmpty()){
            simpleTimeFormat = new SimpleDateFormat(format);
            this.time = simpleTimeFormat.format(t);
        }else{
            simpleTimeFormat = new SimpleDateFormat("HH.mm.ss.SSS");
            this.time = simpleTimeFormat.format(t);
        }
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public void setHw(String hw) {
        this.hw = hw;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setSlaveId(String slaveId) {
        this.slaveId = slaveId;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setTestAction(String testAction) {
        this.testAction = testAction;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public void setUsedMemory(String usedMemory) {
        this.usedMemory = usedMemory;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime, long accuracy) {
        this.endTime = endTime;
        long time = this.endTime - this.startTime - accuracy/2;
        if(time > 0)
            this.totalTime = time;
        else
            this.totalTime = this.endTime - this.startTime;
    }

    public void setTestResult(boolean testResult) {
        this.testResult = testResult;
    }

    public void setTestCycle(String testCycle) {
        this.testCycle = testCycle;
    }

    @Override
    public String toString() {
        return  dateFieldName + getDate() +
                ", " + timeFieldName + getTime() +
                ", " + buildFieldName + getBuild() +
                ", " + hwFieldName + getHw() +
                ", " + osFieldName + getOs() +
                ", " + netFieldName + getNet() +
                ", " + deviceIdFieldName + getDeviceId() +
                ", " + slaveIdFieldName + getSlaveId() +
                ", " + testIdFieldName + getTestId() +
                ", " + testNameFieldName + getTestName() +
                ", " + testActionFieldName + getTestAction() +
                ", " + testDataFieldName + getTestData() +
                ", " + usedMemoryFieldName + getUsedMemory() +
                ", " + startTimeFieldName + getStartTime() +
                ", " + endTimeFieldName + getEndTime() +
                ", " + totalTimeFieldName + getTotalTime() +
                ", " + testResultFieldName + (getTestResult() ? "pass" : "fail") +
                ", " + testCycleFieldName + getTestCycle();
    }
}
