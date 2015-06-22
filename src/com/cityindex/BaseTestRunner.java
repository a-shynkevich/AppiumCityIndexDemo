package com.cityindex;

import com.cityindex.exception.TestException;
import com.cityindex.param.ParamsParser;
import com.cityindex.screen.Screen;
import com.cityindex.tests.SmokeTests;



public class BaseTestRunner {



    public static void main (String... args){
        String[] arguments = args;
            runTest(arguments);
    }

    protected static void runTest(String [] args) {
        Screen screen = null;
        ParamsParser paramsParser = ParamsParser.getInstance();
        paramsParser.parse(args);
        switch (paramsParser.getTestClass()){
            case "SmokeTests":
            screen = new SmokeTests(args);
                break;
            case "":
                break;
        }
//        screen.setUp();
        try {
            screen.runTest(paramsParser.getTestId());
        } catch (TestException e) {
            e.printStackTrace();
        }
        screen.tearDown();
    }


}
