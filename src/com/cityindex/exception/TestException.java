package com.cityindex.exception;


import io.appium.java_client.AppiumDriver;

import java.text.SimpleDateFormat;

public class TestException extends Exception{
    AppiumDriver driver;
    private String errorMessage;

    public TestException(String errorMessage, AppiumDriver driver) {
        super(errorMessage);
        this.driver = driver;
        this.errorMessage = errorMessage.replaceAll(" ", "_").replaceAll("\\.", "").replaceAll(",", "");
    }

    public TestException failTest() {

        StackTraceElement[] stackTrace = this.getStackTrace();
        System.out.println("Exception in thread \"main\" " + TestException.class.getName() + ": " + this.getMessage());
        for(StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement.toString());
        }
        driver.quit();
        return this;
    }


    public TestException retest() {
        StackTraceElement[] stackTrace = this.getStackTrace();
        System.out.println("Exception in thread \"main\" " + TestException.class.getName() + ": " + this.getMessage());
        for(StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement.toString());
        }
        driver.quit();
        return this;
    }

    public String getFormattedTime() {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd.MM.yyyy HH-mm-ss");
        return simpleFormatter.format(System.currentTimeMillis());
    }

}


