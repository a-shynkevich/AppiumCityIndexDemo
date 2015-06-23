package com.cityindex;

import com.cityindex.exception.TestException;
import com.cityindex.param.ParamsParser;
import com.cityindex.screen.Screen;
import com.cityindex.tests.SmokeTestsRunner;



public class BaseTestRunner {



    public static void main (String... args){
        String[] arguments = args;
        runTest(arguments);
    }

    protected static void runTest(String [] args) {
        TestRunner testRunner = null;
        ParamsParser paramsParser = ParamsParser.getInstance();
        paramsParser.parse(args);
        switch (paramsParser.getTestClass()){
            case "SmokeTestsRunner":
                testRunner = new SmokeTestsRunner();
                testRunner.setArguments(args);
                break;
            case "":
                break;
        }
        testRunner.setUp();
        try {
            testRunner.runTest(paramsParser.getTestId());
        } catch (TestException e) {
            e.printStackTrace();
        }
        testRunner.tearDown();
    }


}
