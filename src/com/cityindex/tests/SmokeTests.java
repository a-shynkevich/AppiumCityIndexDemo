package com.cityindex.tests;

import com.cityindex.annotation.Condition;
import com.cityindex.annotation.PreCondition;
import com.cityindex.exception.TestException;
import com.cityindex.json.Status;
import com.cityindex.screen.Login;

public class SmokeTests extends Login {

    @Override
    public void runTest(String testMethod) throws TestException {
        switch (testMethod){
            case "12345": testCase12345();
                break;
            case "":
                break;
        }
    }

    @PreCondition(preConditions = {Condition.NONE} ,
            testTitle = "Test Demo",
            testId = 12345)
    public void testCase12345() throws TestException {
        if(!signIn("dm538902", "password")) {
            testManager.failTest("LOGIN FAILED");
        }
        testManager.testCaseInfo.setStatusId(Status.PASSED);
    }
}
