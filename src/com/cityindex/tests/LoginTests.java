package com.cityindex.tests;

import com.cityindex.annotation.Condition;
import com.cityindex.annotation.PreCondition;
import com.cityindex.exception.TestException;
import com.cityindex.json.Status;
import com.cityindex.screen.Login;

public class LoginTests extends Login {
    public LoginTests(String... args) {
        super(args);
    }

    @Override
    public void runTest(String testMethod) throws TestException {
//        super.setUp();
        switch (testMethod){
            case "testDemo": testCase12345();
                break;
            case "":
                break;
        }
//        super.tearDown();

    }

    @PreCondition(preConditions = {Condition.NONE} ,
            testTitle = "Test Demo",
            testId = 12345)
    public void testCase12345() throws TestException {
        if(!signIn()) {
            testManager.failTest("LOGIN FAILED");
        }
        testManager.testCaseInfo.setStatusId(Status.PASSED);
    }
}
