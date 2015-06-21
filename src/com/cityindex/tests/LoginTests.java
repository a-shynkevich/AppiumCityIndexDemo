package com.cityindex.tests;

import com.cityindex.exception.TestException;
import com.cityindex.json.Status;
import com.cityindex.manager.TestManager;
import com.cityindex.screen.Login;

public class LoginTests extends Login {


    public LoginTests(TestManager testManager) {
        super(testManager);
    }

    public void runTest(String testMethod) throws TestException {
        switch (testMethod){
            case "testDemo": testDemo();
                break;
            case "":
                break;
        }
    }

    public void testDemo() throws TestException {
        if(!signIn()) {
            testManager.failTest("LOGIN FAILED");
        }
        testManager.testCaseInfo.setStatusId(Status.PASSED);
    }
}
