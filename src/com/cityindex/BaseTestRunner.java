package com.cityindex;

import com.cityindex.exception.TestException;
import com.cityindex.manager.TestManager;
import com.cityindex.screen.Screen;
import com.cityindex.tests.LoginTests;



public class BaseTestRunner {



    public static void main (String... args){
        String[] arguments = args;
            runTest(arguments);
    }

    protected static void runTest(String [] args) {
        Screen screen = null;

        switch ("LoginTests"){
            case "LoginTests":
            screen = new LoginTests(args);
                break;
            case "":
                break;
        }
        screen.setUp();
        try {
            screen.runTest("testDemo");
        } catch (TestException e) {
            e.printStackTrace();
        }
        screen.tearDown();
    }


}
