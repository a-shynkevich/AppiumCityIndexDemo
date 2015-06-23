package com.cityindex.tests;

import com.cityindex.TestRunner;
import com.cityindex.annotation.Condition;
import com.cityindex.annotation.PreCondition;
import com.cityindex.exception.TestException;
import com.cityindex.json.Status;
import com.cityindex.param.ConfigParam;
import com.cityindex.screen.Login;

public class SmokeTestsRunner extends TestRunner {

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
            testTitle = "Test Demo Login",
            testId = 12345,
            login = ConfigParam.LOGIN,
            password = ConfigParam.PASSWORD)

    public void testCase12345() throws TestException {
       new LoginTests(testManager).checkLogin();
    }

    @PreCondition(preConditions = {Condition.NONE} ,
            testTitle = "Test Demo with Login precondition",
            testId = 54321,
            login = ConfigParam.LOGIN,
            password = ConfigParam.PASSWORD)
    public void testCase54321() throws TestException {
        new WatchlistTests(testManager).checkCreateNewWatchList();
    }
}
